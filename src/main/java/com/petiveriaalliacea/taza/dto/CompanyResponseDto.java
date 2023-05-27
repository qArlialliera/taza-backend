package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Review;
import com.petiveriaalliacea.taza.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.UUID;
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CompanyResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private UUID photo;
    private Collection<Category> categories;
    private Collection<Review> reviews;
    private User user;
    private boolean isActive;
    private double rate;
}
