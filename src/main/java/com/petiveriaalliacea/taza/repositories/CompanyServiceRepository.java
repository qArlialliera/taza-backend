package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.CompanyService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyServiceRepository extends JpaRepository<CompanyService, Long> {
    Optional<List<CompanyService>> findByCompanyId(Long companyId);
}
