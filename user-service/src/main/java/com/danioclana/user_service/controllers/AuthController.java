package com.danioclana.user_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danioclana.user_service.dtos.LoginDTO;
import com.danioclana.user_service.dtos.UserDTO;
import com.danioclana.user_service.infra.security.TokenService;
import com.danioclana.user_service.models.User;
import com.danioclana.user_service.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.findByEmail(loginDTO.email());
        if(user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if(!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        String token = tokenService.generateToken(user);

        return ResponseEntity.ok().body(token);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {

        if(userService.existsByEmail(userDTO.email())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        User user = new User();
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setPassword(passwordEncoder.encode(userDTO.password()));

        userService.save(user);

        return ResponseEntity.ok().body("User created");
    }

 

}
