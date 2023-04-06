package com.petiveriaalliacea.taza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    String accessToken;
    String refreshToken;
    Collection<SimpleGrantedAuthority> roles;
}
