package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.Role;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.services.impl.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.addRole(role));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user-details")
    public ResponseEntity<User> getUser(){
        return ResponseEntity.ok().body(userService.getUser());
    }

    @PutMapping("/edit/profile")
    public ResponseEntity editUser(@RequestBody UserRequestDto userEdit){
        return ResponseEntity.ok(userService.editUser(userEdit));
    }

    @DeleteMapping("/delete/profile")
    private ResponseEntity deleteCompany(){
        return ResponseEntity.ok(userService.deleteUser());
    }

    @PutMapping("/role/make-admin/{id}")
    public ResponseEntity<?> addAdminRole(@PathVariable Long id){
        return ResponseEntity.ok(userService.addAdminRole(id));
    }
}


@Data
class RoleToUser{
    private String username;
    private String roleName;
}
