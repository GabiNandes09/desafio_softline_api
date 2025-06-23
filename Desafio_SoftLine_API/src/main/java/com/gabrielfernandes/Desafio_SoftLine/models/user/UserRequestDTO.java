package com.gabrielfernandes.Desafio_SoftLine.models.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Usuário é obrigatório")
    private String username;

    @NotBlank(message = "Senha é obrigatório")
    private String password;
}
