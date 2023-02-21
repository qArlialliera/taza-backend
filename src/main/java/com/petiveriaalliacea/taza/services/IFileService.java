package com.petiveriaalliacea.taza.services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IFileService {
    UUID save(MultipartFile saveFile);

    Resource get(UUID id);

    ResponseEntity<Resource> getResponse (UUID id);

    ResponseEntity<byte[]> getPhoto (UUID id);

    boolean existsById(UUID id);
}
