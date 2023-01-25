package com.petiveriaalliacea.taza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CompanyRequestDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Collection<String> categories = new ArrayList<>();

}
