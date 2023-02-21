package com.petiveriaalliacea.taza.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IFileService {
    UUID save(MultipartFile saveFile);

    Resource get(UUID id);

    boolean existsById(UUID id);
}
