package com.petiveriaalliacea.taza.rest;

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
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

//    @PostMapping("/user/register")
//    public ResponseEntity<User> register(@RequestBody User user){
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/register").toUriString());
//        return ResponseEntity.created(uri).body(userService.register(user));
//    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.addRole(role));
    }

//    @PostMapping("/role/addtouser")
//    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUser form){
//        userService.addRoleToUser(form.getUsername(), form.getRoleName());
//        return ResponseEntity.ok().build();
//    }
}


@Data
class RoleToUser{
    private String username;
    private String roleName;
}
