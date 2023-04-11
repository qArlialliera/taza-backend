package com.petiveriaalliacea.taza.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;

@Table(name = DATABASE_PREFIX + "special_offer")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialOffer extends BaseEntity<Long> {
    private String offer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

}
