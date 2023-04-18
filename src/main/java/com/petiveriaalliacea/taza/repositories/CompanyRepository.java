package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);

    Optional<Company> findByUser_Id(Long id);
}


