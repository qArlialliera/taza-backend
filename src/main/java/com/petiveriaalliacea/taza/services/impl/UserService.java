package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.entities.Role;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.mappers.UserMapper;
import com.petiveriaalliacea.taza.repositories.RoleRepository;
import com.petiveriaalliacea.taza.repositories.UserRepository;
import com.petiveriaalliacea.taza.security.PasswordEncoder;
import com.petiveriaalliacea.taza.services.IUserService;
import com.petiveriaalliacea.taza.utils.UserObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRequestDto userDto) {
        boolean userExists = userRepository.findByUsername(userDto.getUsername()).isPresent();
        if(userExists){
            throw new IllegalStateException("User with this username already exists!");
        }
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(userDto.getPassword());
        User user = UserObjectMapper.convertToUserDto(userDto);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

//    @Override
//    public void addRoleToUser(String username, String roleName) {
//        log.info("Adding role {} to the user {}", roleName, username);
//        User user = userRepository.findByUsername(username);
//        Role role = roleRepository.findByName(roleName);
//        user.getRoles().add(role);
//    }

//    @Override
//    public User getUser(String username) {
////        log.info("Fetching the user {}", username);
//        return userRepository.findByUsername(username);
//    }

    @Override
    public List<User> getUsers() {
//        log.info("Fetching all users");
        return userRepository.findAll();
    }

}
