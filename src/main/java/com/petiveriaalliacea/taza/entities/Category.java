package com.petiveriaalliacea.taza.entities;

import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;

@Table(name = DATABASE_PREFIX + "category")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity<Long> {
    private String name;
}
