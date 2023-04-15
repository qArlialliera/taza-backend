package com.petiveriaalliacea.taza.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = DATABASE_PREFIX + "order")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "company_service_id", referencedColumnName = "id")
    private CompanyService companyService;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Date date;
    private String address;
    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatus status;
    private double area;
    private int rooms;
    private boolean withPet;
    @ManyToMany(fetch = EAGER)
    private Collection<CompanyService> additionalServices = new ArrayList<>();
    private String comment;
}
