package com.danioclana.email_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danioclana.email_service.dtos.MailDTO;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody MailDTO mailDTO) {
        var message = new SimpleMailMessage();
        
        message.setFrom("noreply@omsproject.com"); 
        message.setTo(mailDTO.to());
        message.setSubject(mailDTO.subject());
        message.setText(mailDTO.content());

        mailSender.send(message);

        return ResponseEntity.ok().body("Mail sent to " + mailDTO.to());
    }
    
}

