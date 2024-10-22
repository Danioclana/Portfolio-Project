package com.danioclana.user_service.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {
    
    public static boolean isValid(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
}
