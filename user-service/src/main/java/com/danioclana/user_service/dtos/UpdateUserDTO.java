package com.danioclana.user_service.dtos;

import com.danioclana.user_service.utils.EmailValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
    @NotNull(message = "Name cannot be null or empty")
    @NotBlank(message = "Name cannot be null or empty")
    String name,
    @NotNull(message = "Email cannot be null or empty")
    @NotBlank(message = "Email cannot be null or empty")
    String email
) {
    public UpdateUserDTO {
        if (!EmailValidator.isValid(email))
            throw new IllegalArgumentException("Invalid email");
    }
}
