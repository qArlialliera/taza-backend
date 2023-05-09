package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.Comment;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReviewDto {
    private Long id;
    private User user;
    private Company company;
    private double rate;
    private Collection<Comment> comments = new ArrayList<>();
}
