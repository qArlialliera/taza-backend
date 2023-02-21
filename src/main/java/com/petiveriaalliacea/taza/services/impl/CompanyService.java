package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.repositories.CategoryRepository;
import com.petiveriaalliacea.taza.repositories.CompanyRepository;
import com.petiveriaalliacea.taza.services.ICompanyService;
import com.petiveriaalliacea.taza.utils.Mapper;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyService implements ICompanyService {
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final Mapper mapper;

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }
    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).get();
    }
    @Override
    public Company addNewCompany(CompanyRequestDto companyDto){
//        Collection<Category> categories = new ArrayList<>();
//        for (Category category : companyDto.getCategories()){
//            categories.add(categoryRepository.findByName(category));
//        }
        Company company = mapper.toCompany(companyDto);
        company.setCategories(companyDto.getCategories());

        return companyRepository.save(company);
    }
    @Override
    public Company editCompany(Long id, Company dto) {
//        return companyRepository.findById(id).map(
//                companyEdit -> {
//                    companyEdit.setName(company.getName());
//                    companyEdit.setEmail(company.getEmail());
//                    companyEdit.setAddress(company.getAddress());
//                    companyEdit.setPhoneNumber(company.getPhoneNumber());
//                    return companyRepository.save(companyEdit);
//                }
//        ).orElseGet(() -> {
//            company.setId(id);
//            return companyRepository.save(company);
//        });
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            if (!StringUtils.isEmpty(dto.getName())) {
                company.get().setName(dto.getName());
            }
            if (!StringUtils.isEmpty(dto.getEmail())) {
                company.get().setEmail(dto.getEmail());
            }
            if (!StringUtils.isEmpty(dto.getAddress())) {
                company.get().setAddress(dto.getAddress());
            }
            if (!StringUtils.isEmpty(dto.getPhoneNumber())) {
                company.get().setPhoneNumber(dto.getPhoneNumber());
            }
        }
        return companyRepository.save(company.get());

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
