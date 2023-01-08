package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.repositories.CompanyRepository;

import java.util.List;

public class CompanyService {

    private CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

}
