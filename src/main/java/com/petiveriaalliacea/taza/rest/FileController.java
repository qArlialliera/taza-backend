package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.entities.Order;
import com.petiveriaalliacea.taza.services.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

import static com.petiveriaalliacea.taza.utils.Constants.FILE_UPLOAD_FOLDER;
import static com.petiveriaalliacea.taza.utils.Constants.PUBLIC_API_ENDPOINT;

@RestController
@RequestMapping(PUBLIC_API_ENDPOINT + "/file")
@RequiredArgsConstructor
public class FileController {
    private final IFileService fileService;

    @PostMapping(value = "/save",  consumes = "multipart/form-data")
    public ResponseEntity<UUID> save(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(fileService.save(file));
    }

    @GetMapping(value = "/get/{uuid}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> get(@PathVariable("uuid") UUID id) {

        return fileService.getResponse(id);
    }

    @GetMapping(value = "/photo/get/{uuid}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("uuid") UUID id) {

        return fileService.getPhoto(id);
    }
}
