package com.petiveriaalliacea.taza.rest;


import com.petiveriaalliacea.taza.dto.AuthRequestDto;
import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.security.JwtUtils;
import com.petiveriaalliacea.taza.services.impl.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Data
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @Autowired
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDto request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        if (authentication.isAuthenticated()){
            System.out.println("ok");
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        System.out.println("bad");
        return ResponseEntity.status(400).body("Some error has occurred");
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequestDto user){
        return ResponseEntity.ok().body(userService.register(user));
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AuthController;
    }

}
