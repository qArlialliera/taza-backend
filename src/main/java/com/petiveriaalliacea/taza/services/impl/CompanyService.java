package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.CompanyDto;
import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.dto.CompanyResponseDto;
import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.Review;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.repositories.CategoryRepository;
import com.petiveriaalliacea.taza.repositories.CompanyRepository;
import com.petiveriaalliacea.taza.repositories.CompanyServiceRepository;
import com.petiveriaalliacea.taza.repositories.UserRepository;
import com.petiveriaalliacea.taza.security.JwtUtils;
import com.petiveriaalliacea.taza.services.ICompanyService;
import com.petiveriaalliacea.taza.services.IFileService;
import com.petiveriaalliacea.taza.utils.Mapper;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyService implements ICompanyService {
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final CompanyServiceRepository companyServiceRepository;
    private final UserRepository userRepository;
    private final IFileService fileService;
    private final Mapper mapper;

    @Override
    public List<CompanyResponseDto> getCompanies() {
        List<CompanyResponseDto> companiesList = companyRepository.findAll()
                .stream().map(mapper
                ::toCompanyResponseDto).collect(Collectors.toList());

        for(CompanyResponseDto company : companiesList)
            if(company.getReviews() != null && company.getReviews().size() > 0) {
                Collection<Review> reviewList = company.getReviews();
                double companyReviewSum = 0;
                for (Review review : reviewList) {
                    companyReviewSum += review.getRate();
                }
                double companyReview = companyReviewSum/reviewList.size();
                company.setRate(companyReview);
            }else {
                company.setRate(0);
            }

        return companiesList;
    }
    @Override
    public CompanyDto getCompany(Long id) {
        return mapper.toCompanyDto(companyRepository.findById(id).get());
    }

    @Override
    public List<CompanyDto> getCompaniesByCategory(Long id) {
        List<Company> companies = new ArrayList<>();
        Category category = categoryRepository.findById(id).get();

        List<com.petiveriaalliacea.taza.entities.CompanyService> companyServices = companyServiceRepository.findCompanyServiceByCategories(category).get();

        for (com.petiveriaalliacea.taza.entities.CompanyService service : companyServices){
            for(Company company : service.getCompany()){
                companies.add(company);
            }
        }

        return companies.stream().map(mapper::toCompanyDto).collect(Collectors.toList());
    }

    @Override
    public CompanyDto getUserCompany(String token) {
        User user = userRepository.findByUsername(JwtUtils.getUsername(token))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user!"));
        Optional<Company> company = companyRepository.findByUser_Id(user.getId());
        return mapper.toCompanyDto(company.get());
    }

    @Override
    public boolean getUserCompanyExist(String token) {
        User user = userRepository.findByUsername(JwtUtils.getUsername(token))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user!"));
        Optional<Company> company = companyRepository.findByUser_Id(user.getId());
        if(company.isPresent()) return true;

        return false;
    }

    @Override
    public CompanyDto addNewCompany(String token, CompanyRequestDto companyDto){
        Company company = mapper.toCompany(companyDto);
        company.setCategories(companyDto.getCategories());
        company.setActive(false);
        User user = userRepository.findByUsername(JwtUtils.getUsername(token))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user!"));
        company.setUser(user);
        return mapper.toCompanyDto(companyRepository.save(company));
    }
    @Override
    public CompanyDto makeCompanyActive(Long id){
        Optional<Company> company = companyRepository.findById(id);
        company.ifPresent(value -> value.setActive(true));
        return mapper.toCompanyDto(companyRepository.save(company.get()));
    }
    @Override
    public CompanyDto editCompany(Long id, CompanyDto dto) {
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
            if (dto.getCategories() != null) {
                company.get().setCategories(dto.getCategories());
            }
            if (dto.isActive() != company.get().isActive()) {
                company.get().setActive(dto.isActive());
            }
        }
        return mapper.toCompanyDto(companyRepository.save(company.get()));

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
