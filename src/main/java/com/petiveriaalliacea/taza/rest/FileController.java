package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.entities.Order;
import com.petiveriaalliacea.taza.services.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.petiveriaalliacea.taza.utils.Constants.PUBLIC_API_ENDPOINT;

@RestController
@RequestMapping(PUBLIC_API_ENDPOINT + "/file")
@RequiredArgsConstructor
public class FileController {
    private final IFileService fileService;

    @PostMapping("/save")
    public ResponseEntity<UUID> save(@RequestPart MultipartFile file) {
        return ResponseEntity.ok(fileService.save(file));
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<Resource> get(@PathVariable("uuid") UUID id) {
        return ResponseEntity.ok(fileService.get(id));
    }
}
