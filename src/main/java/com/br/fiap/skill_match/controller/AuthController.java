package com.br.fiap.skill_match.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.fiap.skill_match.model.Credential;
import com.br.fiap.skill_match.model.Token;
import com.br.fiap.skill_match.model.User;
import com.br.fiap.skill_match.service.TokenService;

@RestController
public class AuthController {
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Token login(@RequestBody Credential credentials){
        var auth = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
        var user = (User) authenticationManager.authenticate(auth).getPrincipal();

        return tokenService.createToken(user);
    }
}
