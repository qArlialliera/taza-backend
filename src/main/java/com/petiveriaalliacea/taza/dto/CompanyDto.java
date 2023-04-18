package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Review;
import com.petiveriaalliacea.taza.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CompanyDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private UUID photo;
    private Collection<Category> categories;
    private Collection<Review> reviews;
    private User user;

}
