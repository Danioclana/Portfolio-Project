package com.danioclana.email_service.dtos;

public record MailDTO(
    String to,
    String subject,
    String content
) {
    public MailDTO {
        if (to == null || to.isBlank()) {
             // TODO: personalize exception
            throw new IllegalArgumentException("To cannot be empty");
        }
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
    }
}
