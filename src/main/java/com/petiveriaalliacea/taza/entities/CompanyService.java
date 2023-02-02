package com.petiveriaalliacea.taza.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyService {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long categoryId;

    private Long companyId;

    private BigDecimal price;


}
