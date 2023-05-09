package com.petiveriaalliacea.taza.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;
import static jakarta.persistence.FetchType.EAGER;

@Table(name = DATABASE_PREFIX + "company")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity<Long> {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private UUID photo;
    @JsonManagedReference(value="category_company")
    @ManyToMany(fetch = EAGER)
    private Collection<Category> categories = new ArrayList<>();
    @JsonManagedReference(value="review_company")
    @OneToMany(mappedBy = "company")
    private Collection<Review> reviews = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isActive;

}
