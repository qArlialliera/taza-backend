package com.petiveriaalliacea.taza.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

}
