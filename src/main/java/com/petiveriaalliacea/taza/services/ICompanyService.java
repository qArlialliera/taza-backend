package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.CompanyDto;
import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.entities.Company;

import java.util.List;
import java.util.UUID;

public interface ICompanyService {
    List<CompanyDto> getCompanies();
    CompanyDto getCompany(Long id);

    List<CompanyDto> getCompaniesByCategory(Long id);

    CompanyDto getUserCompany(String token);

    boolean getUserCompanyExist(String token);

    CompanyDto addNewCompany(String token, CompanyRequestDto companyDto);

    CompanyDto makeCompanyActive(Long id);

    CompanyDto editCompany(Long id, CompanyDto dto);

    String deleteCompany(Long id);

    void addPhoto(Long companyId, UUID photo);

    UUID getPhoto(Long companyId);
}
