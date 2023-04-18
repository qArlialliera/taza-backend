package com.petiveriaalliacea.taza.dto;


import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String city;
    private String address;
    private String phoneNumber;
}
