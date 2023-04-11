package com.petiveriaalliacea.taza.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = DATABASE_PREFIX + "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String city;
    private String address;
    private UUID photo;
    @ManyToMany(fetch = EAGER)
    @JsonIgnore
    private Collection<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private Collection<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private Collection<Order> orders = new ArrayList<>();

}
