package com.petiveriaalliacea.taza.mappers;

import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.entities.Company;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CompanyMapper {
    public static Company convertCompanyDto(CompanyRequestDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .address(companyDto.getAddress())
                .phoneNumber(companyDto.getPhoneNumber())
                .build();
    }

}
