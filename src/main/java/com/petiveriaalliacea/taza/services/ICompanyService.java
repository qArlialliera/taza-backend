package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.entities.Company;

import java.util.List;
import java.util.UUID;

public interface ICompanyService {
    List<Company> getCompanies();
    Company getCompany(Long id);

    List<Company> getCompaniesByCategory(Long id);

    Company addNewCompany(CompanyRequestDto companyDto);

    Company editCompany(Long id, Company company);
    String deleteCompany(Long id);

    void addPhoto(Long companyId, UUID photo);

    UUID getPhoto(Long companyId);
}
