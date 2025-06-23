package com.gabrielfernandes.Desafio_SoftLine.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserResponseDTO {
    private int id;
    private String username;
}
