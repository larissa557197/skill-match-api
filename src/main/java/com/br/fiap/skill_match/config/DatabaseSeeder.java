package com.br.fiap.skill_match.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.fiap.skill_match.model.Feedback;
import com.br.fiap.skill_match.model.User;
import com.br.fiap.skill_match.repository.FeedbackRepository;
import com.br.fiap.skill_match.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Cria usuários com senha criptografada
        String password = passwordEncoder.encode("123456");
        var lari = User.builder().email("lari@fiap.com.br").password(password).build();
        var joao = User.builder().email("joao@fiap.com.br").password(password).build();

        // Salva os usuários no banco
        userRepository.save(lari);
        userRepository.save(joao);
        // Cria e salva feedbacks
        feedbackRepository.saveAll(List.of(
            Feedback.builder()
                .userName("João Silva")
                .comment("Excelente trabalho em equipe!")
                .rating(5)
                .build(),
            Feedback.builder()
                .userName("Maria Oliveira")
                .comment("Muito proativo e colaborativo")
                .rating(4)
                .build(),
            Feedback.builder()
                .userName("Pedro Souza")
                .comment("Ótima comunicação")
                .rating(5)
                .build()
        ));
    }
}
