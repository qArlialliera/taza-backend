package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileData, UUID> {
}
