package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.Company;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SpecialOfferDto {
    private Long id;
    private String offer;
    private Company company;
}
