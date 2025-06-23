package com.gabrielfernandes.Desafio_SoftLine.models.client;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class ClientRequestDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O nome fantasia é obrigatório")
    private String fantasyName;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(min = 14, max = 14, message = "O CNPJ deve conter exatamente 14 dígitos")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter apenas números")
    private String document;

    @Valid
    private AddressRequestDTO address;
}
