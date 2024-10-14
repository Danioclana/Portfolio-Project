package com.danioclana.user_service.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danioclana.user_service.dtos.PasswordResetDTO;
import com.danioclana.user_service.dtos.UpdateUserDTO;
import com.danioclana.user_service.models.User;
import com.danioclana.user_service.repositories.UserRepository;
import com.danioclana.user_service.services.EmailService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {   

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {

        if(!userRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("User not found");
        }

        emailService.accountDeleted(userRepository.findById(id).get().getEmail());
        
        userRepository.deleteById(id);

        return ResponseEntity.ok().body("User deleted");
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") UUID id, @RequestBody @Valid UpdateUserDTO userUpdateDTO) {

        if (userRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userRepository.findById(id).get();

        user.setName(userUpdateDTO.name());
        user.setEmail(userUpdateDTO.email());
        userRepository.save(user);

        emailService.dataChanged(user.getEmail());

        return ResponseEntity.ok().body("User updated successfully");
    }

    @PutMapping("/reset-password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") UUID id, @RequestBody @Valid PasswordResetDTO passwordResetDTO) {
        if (userRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userRepository.findById(id).get();

        user.setPassword(passwordResetDTO.newPassword());
        userRepository.save(user);

        emailService.passwordChanged(user.getEmail());

        return ResponseEntity.ok().body("Password reset successfully");
    }

    // TODO: Fix the update and reset password methods 
}
