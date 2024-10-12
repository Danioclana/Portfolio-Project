package com.danioclana.email_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.danioclana.email_service.dtos.MailDTO;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(MailDTO mailDTO) {
        var message = new SimpleMailMessage();
        
        message.setFrom("noreply@omsproject.com"); 
        message.setTo(mailDTO.to());
        message.setSubject(mailDTO.subject());
        message.setText(mailDTO.content());

        try {
            mailSender.send(message); 
        } catch (Exception e) {
            throw new RuntimeException("Error sending email: ");
        }

    }
}
