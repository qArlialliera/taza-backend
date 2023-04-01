package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.Category;
import lombok.*;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RequestDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Collection<Category> categories;
    private String remarks;
    private String categorySuggest;
}
