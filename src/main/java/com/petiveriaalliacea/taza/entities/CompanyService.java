package com.petiveriaalliacea.taza.entities;

import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;
import static jakarta.persistence.FetchType.EAGER;

@Table(name = DATABASE_PREFIX + "company_service")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyService extends BaseEntity<Long> {

    @ManyToMany(fetch = EAGER)
    private Collection<Category> categories = new ArrayList<>();

    @ManyToMany(fetch = EAGER)
    private Collection<Company> company = new ArrayList<>();

    private BigDecimal price;

    private boolean isAdditionalService;

    @OneToMany(mappedBy = "companyService")
    private Collection<Order> orders = new ArrayList<>();

}
