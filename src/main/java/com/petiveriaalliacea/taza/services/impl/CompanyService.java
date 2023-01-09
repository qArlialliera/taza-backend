package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.repositories.CompanyRepository;
import com.petiveriaalliacea.taza.services.ICompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyService implements ICompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }
    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).get();
    }
    @Override
    public Company addNewCompany(Company newCompany){
        return companyRepository.save(newCompany);
    }
    @Override
    public Company editCompany(Long id, Company company) {
        return companyRepository.findById(id).map(
                companyEdit -> {
                    companyEdit.setName(company.getName());
                    companyEdit.setEmail(company.getEmail());
                    companyEdit.setAddress(company.getAddress());
                    companyEdit.setPhoneNumber(company.getPhoneNumber());
                    return companyRepository.save(companyEdit);
                }
        ).orElseGet(() -> {
            company.setId(id);
            return companyRepository.save(company);
        });

    }
    @Override
    public String deleteCompany(Long id) {
        if(companyRepository.findById(id).isPresent()) {
            companyRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Company not found!";
    }
}
