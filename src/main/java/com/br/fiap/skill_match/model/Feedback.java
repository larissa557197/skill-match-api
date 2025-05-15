package com.br.fiap.skill_match.model;

// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.OffsetDateTime;

// import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String userName;

    @NotBlank(message = "O comentário é obrigatório")
    private String comment;

    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    private int rating;


}
