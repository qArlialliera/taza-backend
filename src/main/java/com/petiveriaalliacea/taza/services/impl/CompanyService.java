package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.repositories.CategoryRepository;
import com.petiveriaalliacea.taza.repositories.CompanyRepository;
import com.petiveriaalliacea.taza.repositories.CompanyServiceRepository;
import com.petiveriaalliacea.taza.services.ICompanyService;
import com.petiveriaalliacea.taza.services.IFileService;
import com.petiveriaalliacea.taza.utils.Mapper;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyService implements ICompanyService {
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final CompanyServiceRepository companyServiceRepository;
    private final IFileService fileService;
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
    public List<Company> getCompaniesByCategory(Long id) {
        List<Company> companies = new ArrayList<>();
        Category category = categoryRepository.findById(id).get();

        List<com.petiveriaalliacea.taza.entities.CompanyService> companyServices = companyServiceRepository.findCompanyServiceByCategories(category).get();

        for (com.petiveriaalliacea.taza.entities.CompanyService service : companyServices){
            for(Company company : service.getCompany()){
                companies.add(company);
            }
        }

        return companies;
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
            if (!(dto.getCategories()).isEmpty()) {
                company.get().setCategories(dto.getCategories());
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

    @Override
    public void addPhoto(Long companyId, UUID photo) {
        if (!fileService.existsById(photo)) {
            // TODO: error that file not exists
        }
        Company company = companyRepository.findById(companyId).orElse(null);
        if (isNull(company)) {
            // TODO: throw error
        }
        company.setPhoto(photo);
        companyRepository.save(company);
    }

    @Override
    public UUID getPhoto(Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (isNull(company)) {
            // TODO: throw error
        }
        return company.getPhoto();
    }

}
