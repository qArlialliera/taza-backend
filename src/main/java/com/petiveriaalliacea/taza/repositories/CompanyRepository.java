package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
}


