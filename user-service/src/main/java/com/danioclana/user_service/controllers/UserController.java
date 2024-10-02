package com.danioclana.user_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danioclana.user_service.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {   

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(userService.findAll());
    }

}
