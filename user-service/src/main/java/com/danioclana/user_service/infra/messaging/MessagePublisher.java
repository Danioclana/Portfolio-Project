package com.danioclana.user_service.infra.messaging;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendEmail(String to, String subject, String content) {
        try {
            Map<String, String> message = new HashMap<>();
            message.put("to", to);
            message.put("subject", subject);
            message.put("content", content);

            String jsonMessage = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, jsonMessage);
        } catch (Exception e) {
            LoggerFactory.getLogger(MessagePublisher.class).error("Failed to send email message", e);
        }
    }
}

