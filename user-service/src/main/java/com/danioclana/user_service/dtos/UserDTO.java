package com.danioclana.user_service.dtos;

import com.danioclana.user_service.utils.EmailValidator;
import com.danioclana.user_service.utils.PasswordValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
    @NotNull(message = "Name cannot be null or empty")
    @NotBlank(message = "Name cannot be null or empty")
    String name,
    @NotNull(message = "Email cannot be null or empty")
    @NotBlank(message = "Email cannot be null or empty")
    String email,
    @NotNull(message = "Password cannot be null or empty")
    @NotBlank(message = "Password cannot be null or empty")
    String password
) {
    public UserDTO {
        if (!EmailValidator.isValid(email))
            throw new IllegalArgumentException("Invalid email");

        if (!PasswordValidator.isValid(password))
            throw new IllegalArgumentException("The password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number and one special character");
    }

}
