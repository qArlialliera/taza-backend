package com.petiveriaalliacea.taza.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String city;
    private String address;
    @ManyToMany(fetch = EAGER)
    @JsonIgnore
    private Collection<Role> roles = new ArrayList<>();
}
