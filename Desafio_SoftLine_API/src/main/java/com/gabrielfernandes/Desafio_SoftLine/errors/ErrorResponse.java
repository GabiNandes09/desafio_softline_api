package com.gabrielfernandes.Desafio_SoftLine.errors;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ErrorResponse {
    private String erro;
    private String mensagem;
    private String detalhe;
    private LocalDateTime timestamp;
}
