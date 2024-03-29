package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.dto.UserResponseDto;
import com.petiveriaalliacea.taza.entities.Role;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.repositories.RoleRepository;
import com.petiveriaalliacea.taza.repositories.UserRepository;
import com.petiveriaalliacea.taza.security.JwtUtils;
import com.petiveriaalliacea.taza.security.PasswordEncoder;
import com.petiveriaalliacea.taza.services.IFileService;
import com.petiveriaalliacea.taza.services.IUserService;
import com.petiveriaalliacea.taza.utils.Mapper;
import com.petiveriaalliacea.taza.utils.SecurityUtils;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final IFileService fileService;
    private final Mapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addPhoto(UUID photo, String token) {
        if (!fileService.existsById(photo)) {
            // TODO: error that file not exists
        }
        User user = userRepository.findByUsername(JwtUtils.getUsername(token)).orElse(null);
        if (isNull(user)) {
            // TODO: throw error
        }
        user.setPhoto(photo);
        userRepository.save(user);
    }

    @Override
    public UUID getPhoto(String token) {
        User user = userRepository.findByUsername(JwtUtils.getUsername(token)).orElse(null);
        if (isNull(user)) {
            // TODO: throw error
        }
        return user.getPhoto();
    }

    @Override
    public UserResponseDto register(UserRequestDto userDto) {
        boolean userExists = userRepository.findByUsername(userDto.getUsername()).isPresent();
        if (userExists) {
            throw new IllegalStateException("User with this username already exists!");
        }
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(userDto.getPassword());
        User user = mapper.toUser(userDto);
        user.setPassword(encodedPassword);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return mapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto registerCompanyRepresentative(UserRequestDto userDto) {
        boolean userExists = userRepository.findByUsername(userDto.getUsername()).isPresent();
        if (userExists) {
            throw new IllegalStateException("User with this username already exists!");
        }
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(userDto.getPassword());
        User user = mapper.toUser(userDto);
        user.setPassword(encodedPassword);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_COMPANY")));
        return mapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public List<User> getUsers() {
//        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public String deleteUser() {
        Optional<User> user = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin());
        if (userRepository.findById(user.get().getId()).isPresent()) {
            userRepository.deleteById(user.get().getId());
            return "User deleted Successfully!";
        }
        return "User not found!";
    }

    @Override
    public String editUser(UserRequestDto dto) {
        Optional<User> user = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin());
        if (user.isPresent()) {
            log.debug("User id: {}, changing user details from: {}", user.get().getId(), user.get());
            if (!StringUtils.isEmpty(dto.getUsername())) {
                user.get().setUsername(dto.getUsername());
            }
            if (!StringUtils.isEmpty(dto.getCity())) {
                user.get().setCity(dto.getCity());
            }
            if (!StringUtils.isEmpty(dto.getAddress())) {
                user.get().setAddress(dto.getAddress());
            }
            if (!StringUtils.isEmpty(dto.getEmail())) {
                user.get().setEmail(dto.getEmail());
            }
            if (!StringUtils.isEmpty(dto.getFullName())) {
                user.get().setFullName(dto.getFullName());
            }
            userRepository.save(user.get());
            return "Updated successfully!";
        } else {
            return "User does not exist!";
        }
    }

    @Override
    public User getUser() {
        Optional<User> user = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin());
        if (user.isPresent()) return user.get();
        else throw new IllegalStateException("User with this username already exists!");
    }

    @Override
    public String addAdminRole(Long id) {
        log.info("Adding admin role to the user {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Collection<Role> userRoles = user.get().getRoles();
            userRoles.add(roleRepository.findByName("ROLE_ADMIN"));
            user.get().setRoles(userRoles);
            userRepository.save(user.get());
            return "Admin role added to the user " + user.get().getUsername();
        } else
            return "Adding admin role failed! User does not exist.";
    }
}
