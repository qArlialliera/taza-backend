package com.petiveriaalliacea.taza.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;
import static jakarta.persistence.FetchType.EAGER;

@Table(name = DATABASE_PREFIX + "order")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity<Long> {
    @JsonBackReference(value="company_service_order")
    @ManyToOne
    @JoinColumn(name = "company_service_id", referencedColumnName = "id")
    private CompanyService companyService;
    @JsonBackReference(value="user_order")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Date date;
    private String address;
    @JsonBackReference(value="status_order")
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
