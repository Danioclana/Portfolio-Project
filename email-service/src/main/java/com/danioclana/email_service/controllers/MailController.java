package com.danioclana.email_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danioclana.email_service.dtos.MailDTO;
import com.danioclana.email_service.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody MailDTO mailDTO) {
        mailService.sendMail(mailDTO);
        return ResponseEntity.ok().body("Mail sent to " + mailDTO.to());
    }
    
}

