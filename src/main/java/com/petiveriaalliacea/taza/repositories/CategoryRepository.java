package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

}
