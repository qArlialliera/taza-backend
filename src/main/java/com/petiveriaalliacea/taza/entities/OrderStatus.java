package com.petiveriaalliacea.taza.entities;

import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;

@Table(name = DATABASE_PREFIX + "order_status")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus extends BaseEntity<Long> {
    private String name;
    @OneToMany(mappedBy = "status")
    private Collection<Order> orders = new ArrayList<>();


}
