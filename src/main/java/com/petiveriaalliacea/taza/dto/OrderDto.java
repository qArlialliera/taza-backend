package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.CompanyService;
import com.petiveriaalliacea.taza.entities.OrderStatus;
import com.petiveriaalliacea.taza.entities.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static jakarta.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderDto {
    private Long id;
    private CompanyService companyService;
    private User user;
    private Date date;
    private String address;
    private OrderStatus status;
    private double area;
    private int rooms;
    private boolean withPet;
    private Collection<CompanyService> additionalServices;
    private String comment;

}
