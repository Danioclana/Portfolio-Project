package com.danioclana.user_service.dtos;

public record UserDTO(
    String name,
    String email,
    String password
) {
    public UserDTO {
        if (name == null || name.isBlank()) {
            // TODO: personalize exception
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}
