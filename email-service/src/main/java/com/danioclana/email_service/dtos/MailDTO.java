package com.danioclana.email_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MailDTO(
    @NotNull(message = "To cannot be empty")
    @NotBlank(message = "To cannot be empty")
    String to,
    @NotNull(message = "Subject cannot be empty")
    @NotBlank(message = "Subject cannot be empty")
    String subject,
    @NotNull(message = "Content cannot be empty")
    @NotBlank(message = "Content cannot be empty")
    String content
) {}
