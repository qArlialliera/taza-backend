package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.CompanyService;
import com.petiveriaalliacea.taza.repositories.CategoryRepository;
import com.petiveriaalliacea.taza.repositories.CompanyRepository;
import com.petiveriaalliacea.taza.repositories.CompanyServiceRepository;
import com.petiveriaalliacea.taza.services.ICompanyServiceService;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyServiceService implements ICompanyServiceService {
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final CompanyServiceRepository companyServiceRepository;

    @Override
    public List<CompanyService> getCompanyServices(Long companyId) {
        List<CompanyService> companyServices = companyServiceRepository.findAll();
        List<CompanyService> responseServices = new ArrayList<>();
        Company company = companyRepository.findById(companyId).get();
        for(CompanyService service : companyServices){
            for(Company companies : service.getCompany())
                if(companies.getId() == company.getId())
                    responseServices.add(service);
        }
        return responseServices;
    }

    @Override
    public List<CompanyService> getServices() {
        return companyServiceRepository.findAll();
    }

    @Override
    public BigDecimal getPrice(Long companyServiceId) {
        return companyServiceRepository.findById(companyServiceId).get().getPrice();
    }

    @Override
    public CompanyService addNewService(CompanyService companyService) {
        return companyServiceRepository.save(companyService);
    }

    @Override
    public CompanyService editServicePrice(Long id, BigDecimal price) {
        Optional<CompanyService> companyService = companyServiceRepository.findById(id);
        if(companyService.isPresent())
            if (price!=null) {
                companyService.get().setPrice(price);
            }
        return companyServiceRepository.save(companyService.get());
    }

    @Override
    public String deleteService(Long id) {
        if(companyServiceRepository.findById(id).isPresent()) {
            companyServiceRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Company service not found!";
    }


}
