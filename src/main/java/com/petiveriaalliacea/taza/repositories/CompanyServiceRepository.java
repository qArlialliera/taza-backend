package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.CompanyService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyServiceRepository extends JpaRepository<CompanyService, Long> {
    Optional<List<CompanyService>> findCompanyServiceByCompany(Company company);
    Optional<List<CompanyService>> findCompanyServiceByCategories(Category Category);


}
