package com.danioclana.user_service.dtos;

import com.danioclana.user_service.utils.PasswordValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PasswordResetDTO(
    @NotNull(message = "Password cannot be null or empty")
    @NotBlank(message = "Password cannot be null or empty")
    String newPassword
) {
    public PasswordResetDTO {
        if (!PasswordValidator.isValid(newPassword))
            throw new IllegalArgumentException("The password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number and one special character");
    }
}
