package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReviewCommentDto {
    private Long id;
    private User user;
    private Company company;
    private double rate;
    private String text;
}
