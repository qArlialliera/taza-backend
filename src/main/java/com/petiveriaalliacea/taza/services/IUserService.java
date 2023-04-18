package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.dto.UserResponseDto;
import com.petiveriaalliacea.taza.entities.Role;
import com.petiveriaalliacea.taza.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserResponseDto register(UserRequestDto userDto);
    UserResponseDto registerCompanyRepresentative(UserRequestDto userDto);
    String addAdminRole(Long id);
    List<User> getUsers();
    String deleteUser();
    String editUser(UserRequestDto user);
    User getUser();
    void addPhoto(UUID photo, String token);
    UUID getPhoto (String token);
}
