package com.petiveriaalliacea.taza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserViewDto {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String city;
    private String address;
    private UUID photo;
}
