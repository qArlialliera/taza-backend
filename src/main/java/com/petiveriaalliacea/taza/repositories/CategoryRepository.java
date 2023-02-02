package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    Optional<Category> findById(Long id);

}
