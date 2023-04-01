package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findById(Long id);
}
