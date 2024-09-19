package com.danioclana.user_service.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danioclana.user_service.dtos.UserDTO;
import com.danioclana.user_service.models.User;
import com.danioclana.user_service.services.UserService;

@RestController
public class UserController {   

    @Autowired
    private UserService userService;
    
    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        return ResponseEntity.ok(userService.save(user));
    }

}
