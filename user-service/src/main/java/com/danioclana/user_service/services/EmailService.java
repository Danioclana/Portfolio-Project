package com.danioclana.user_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danioclana.user_service.infra.messaging.MessagePublisher;

@Service
public class EmailService {

    @Autowired
    private MessagePublisher messagePublisher;

    public void welcome(String email) {
        messagePublisher.sendEmail(email, "Welcome", "Welcome to our platform!");
    }

    public void login(String email) {
        messagePublisher.sendEmail(email, "Login", "You have logged in!");
    }

    public void passwordChanged(String email) {
        messagePublisher.sendEmail(email, "Password Changed", "Your password has been changed!");
    }

    public void dataChanged(String email) {
        messagePublisher.sendEmail(email, "Data Changed", "Your data has been changed!");
    }

    public void accountDeleted(String email) {
        messagePublisher.sendEmail(email, "Account Deleted", "Your account has been deleted!");
    }
}
