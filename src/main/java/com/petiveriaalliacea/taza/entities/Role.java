package com.petiveriaalliacea.taza.entities;

import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;
import static jakarta.persistence.GenerationType.AUTO;

@Table(name = DATABASE_PREFIX + "role")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity<Long> {
    private String name;
}
