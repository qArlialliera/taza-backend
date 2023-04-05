package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.CompanyService;

import java.math.BigDecimal;
import java.util.List;

public interface ICompanyServiceService {
    List<CompanyService> getCompanyServices(Long id);

    List<CompanyService> getServices();

    BigDecimal getPrice(Long id);

    CompanyService addNewService(CompanyService companyService);

    CompanyService editServicePrice(Long id, BigDecimal price);

    String deleteService(Long id);
}
