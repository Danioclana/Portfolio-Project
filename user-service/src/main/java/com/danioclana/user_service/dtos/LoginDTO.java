package com.danioclana.user_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(
    @NotNull(message = "Email cannot be null or empty")
    @NotBlank(message = "Email cannot be null or empty")
    String email,
    @NotNull(message = "Password cannot be null or empty")
    @NotBlank(message = "Password cannot be null or empty")
    String password
) {}
