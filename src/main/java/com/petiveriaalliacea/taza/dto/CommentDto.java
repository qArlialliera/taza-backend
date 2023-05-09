package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.Review;
import com.petiveriaalliacea.taza.entities.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CommentDto {
    private User user;
    private Long parentId;
    private String text;
    private Review review;
}
