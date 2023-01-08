package com.petiveriaalliacea.taza.utils;

import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass

public class UserObjectMapper {
    public static User convertToUserDto(UserRequestDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .fullName(userDto.getFullName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .city(userDto.getCity())
                .address(userDto.getAddress())
//                .roleDto(convertToRoleDto(user.getRole()))
                .build();
    }

}
