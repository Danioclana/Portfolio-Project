package com.danioclana.user_service.utils;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    public static boolean isValid(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
