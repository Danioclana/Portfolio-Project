package com.danioclana.email_service.infra.messaging;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.danioclana.email_service.dtos.MailDTO;
import com.danioclana.email_service.service.MailService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageListener {

    @Autowired
    private MailService mailService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String jsonMessage) {
        try {
            Map<String, String> message = new ObjectMapper().readValue(jsonMessage, new TypeReference<Map<String, String>>(){});
            String to = message.get("to");
            String subject = message.get("subject");
            String content = message.get("content");
            send(to, subject, content);
        } catch (Exception e) {
            throw new RuntimeException("Error while processing message");
        }
    }

    private void send (String to, String subject, String content) {
        MailDTO mailDTO = new MailDTO(to, subject, content);
        mailService.sendMail(mailDTO);
    }
}

