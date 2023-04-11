package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReviewDto {
    private Long id;
    private User user;
    private Company company;
    private String comment;
    private double rate;
}
