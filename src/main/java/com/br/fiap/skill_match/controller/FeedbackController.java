package com.br.fiap.skill_match.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.br.fiap.skill_match.repository.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.br.fiap.skill_match.model.Feedback;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private FeedbackRepository repository;

    // INDEX - Listar todos os feedbacks
    @GetMapping
    @Operation(summary = "Listar feedbacks", description = "Retorna todos os feedbacks cadastrados")
    @Cacheable("feedbacks")
    public List<Feedback> index() {
        log.info("Listando feedbacks...");
        return repository.findAll();
    }

    // CREATE - Criar novo feedback
    @PostMapping
    @CacheEvict(value = "feedbacks", allEntries = true)
    @Operation(summary = "Cadastrar feedback", responses = @ApiResponse(responseCode = "400", description = "Erro de validação"))
    @ResponseStatus(HttpStatus.CREATED)
    public Feedback create(@RequestBody @Valid Feedback feedback) {
        // if (feedback.getDate() == null) {
        //     feedback.setDate(LocalDateTime.now());
        // }
    log.info("Cadastrando feedback de {}", feedback.getUserName());
    return repository.save(feedback);
}


    // GET por ID
    @GetMapping("{id}")
    @Operation(summary = "Buscar feedback por ID")
    public ResponseEntity<Feedback> get(@PathVariable Long id) {
        log.info("Buscando feedback {}", id);
        return ResponseEntity.ok(getFeedback(id));
    }

    // PUT - Atualizar
    @PutMapping("{id}")
    @Operation(summary = "Atualizar feedback")
    public ResponseEntity<Feedback> update(@PathVariable Long id, @RequestBody @Valid Feedback atualizado) {
        log.info("Atualizando feedback {}", id);
        Feedback existente = getFeedback(id);
        atualizado.setId(id);
        return ResponseEntity.ok(repository.save(atualizado));
    }

    // DELETE - Excluir
    @DeleteMapping("{id}")
    @Operation(summary = "Excluir feedback")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Excluindo feedback {}", id);
        repository.delete(getFeedback(id));
        return ResponseEntity.noContent().build();
    }

    // Método auxiliar
    private Feedback getFeedback(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback não encontrado"));
    }
}
