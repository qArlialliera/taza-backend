package com.petiveriaalliacea.taza.dto;


import com.petiveriaalliacea.taza.entities.Role;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRequestDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String city;
    private String address;
    private String phoneNumber;
    private Collection<Role> roles;
}
