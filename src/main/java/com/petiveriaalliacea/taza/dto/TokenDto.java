package com.petiveriaalliacea.taza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {
    String accessToken;
    String refreshToken;
}
