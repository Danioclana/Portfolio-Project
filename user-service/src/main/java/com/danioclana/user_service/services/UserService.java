package com.danioclana.user_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danioclana.user_service.infra.messaging.MessagePublisher;
import com.danioclana.user_service.models.User;
import com.danioclana.user_service.repositories.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessagePublisher messagePublisher;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(User user) {
        messagePublisher.sendEmail(user.getEmail(), "Bem-vindo ao sistema", "Você foi cadastrado com sucesso!");
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}
