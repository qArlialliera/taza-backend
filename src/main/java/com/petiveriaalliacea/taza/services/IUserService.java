package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.entities.Role;
import com.petiveriaalliacea.taza.entities.User;

import java.util.List;

public interface IUserService {
    User register(UserRequestDto userDto);
    Role addRole(Role role);
//    void addRoleToUser(String username, String roleName);
//    User getUser(String username);
    List<User> getUsers();
}
