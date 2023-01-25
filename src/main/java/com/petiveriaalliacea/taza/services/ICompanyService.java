package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.entities.Company;

import java.util.List;

public interface ICompanyService {
    List<Company> getCompanies();
    Company getCompany(Long id);

    Company addNewCompany(CompanyRequestDto companyDto);

    Company editCompany(Long id, Company company);
    String deleteCompany(Long id);

}
