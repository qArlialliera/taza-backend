package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.FileData;
import com.petiveriaalliacea.taza.repositories.FileRepository;
import com.petiveriaalliacea.taza.services.IFileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.petiveriaalliacea.taza.utils.Constants.FILE_UPLOAD_FOLDER;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService implements IFileService {

    private final FileRepository fileRepository;

    @SneakyThrows // TODO: a proper error
    @Override
    public Resource get(UUID id) {
        FileData fileData = fileRepository.findById(id).orElse(null);
        if (isNull(fileData)) {
            return null; // TODO: a proper error
        }

        Path path = Paths.get(FILE_UPLOAD_FOLDER + fileData.getName());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Resource> getResponse(UUID id) {
        FileData fileData = fileRepository.findById(id).orElse(null);
        if (isNull(fileData)) {
            return null; // TODO: a proper error
        }

        Path path = Paths.get(FILE_UPLOAD_FOLDER + fileData.getName());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        File fileInfo = new File(FILE_UPLOAD_FOLDER + fileData.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileData.getName());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileInfo.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @Override
    public boolean existsById(UUID id) {
        return fileRepository.existsById(id);
    }

    @Override
    public ResponseEntity<byte[]> getPhoto(UUID id) {
        FileData fileData = fileRepository.findById(id).orElse(null);
        if (isNull(fileData)) {
            return null; // TODO: a proper error
        }
        File file = new File(FILE_UPLOAD_FOLDER + fileData.getName());
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        if (fileData.getName().toLowerCase().endsWith(".jpg") || fileData.getName().toLowerCase().endsWith(".jpeg")) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (fileData.getName().toLowerCase().endsWith(".png")) {
            mediaType = MediaType.IMAGE_PNG;
        } else if (fileData.getName().toLowerCase().endsWith(".gif")) {
            mediaType = MediaType.IMAGE_GIF;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);

        // Return the file contents as a response entity
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(fileContent);
    }

    @Override
    @SneakyThrows   // TODO: a proper error
    public UUID save(MultipartFile saveFile) {
        UUID id = UUID.randomUUID();    // generate random UUID to use it as file reference
        FileData fileData = new FileData();
        fileData.setId(id);
        fileData.setName(saveFile.getOriginalFilename());
        fileRepository.save(fileData);

        File localFileData = new File(FILE_UPLOAD_FOLDER + saveFile.getOriginalFilename());

        // Write the file to the local file system
        FileOutputStream outputStream = new FileOutputStream(localFileData);
        outputStream.write(saveFile.getBytes());
        outputStream.close();
        return id;
    }
}
