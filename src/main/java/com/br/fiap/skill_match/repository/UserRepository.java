package com.br.fiap.skill_match.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fiap.skill_match.model.User;



public interface UserRepository  extends JpaRepository <User, Long>{
    

    Optional<User> findByEmail(String email);

}
