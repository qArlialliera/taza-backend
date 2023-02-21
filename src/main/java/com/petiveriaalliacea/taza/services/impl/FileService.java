package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.File;
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
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        File file = fileRepository.findById(id).orElse(null);
        if (isNull(file)) {
            return null; // TODO: a proper error
        }

        Path path = Paths.get(FILE_UPLOAD_FOLDER + file.getName());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }

    @Override
    public boolean existsById(UUID id) {
        return fileRepository.existsById(id);
    }

    @Override
    @SneakyThrows   // TODO: a proper error
    public UUID save(MultipartFile saveFile) {
        UUID id = UUID.randomUUID();    // generate random UUID to use it as file reference
        File file = new File();
        file.setId(id);
        file.setName(saveFile.getName());
        fileRepository.save(file);

        Path path = Paths.get(FILE_UPLOAD_FOLDER + saveFile.getName());
        Files.write(path, saveFile.getBytes());
        return id;
    }
}
