package com.gabrielfernandes.Desafio_SoftLine.models.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AddressRequestDTO {
    @NotBlank(message = "O CEP é obrigatório")
    @Size(min = 8, max = 8, message = "O CEP deve ter exatamente 8 caracteres")
    private String zipCode;

    @NotBlank(message = "A rua é obrigatória")
    private String street;

    @NotBlank(message = "O número é obrigatório")
    private String number;

    @NotBlank(message = "O bairro é obrigatório")
    private String neighborhood;

    @NotBlank(message = "A cidade é obrigatória")
    private String city;

    @Pattern(regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$", message = "Estado inválido. Use a sigla oficial (ex: SP, RJ, DF).")
    @NotBlank(message = "O estado é obrigatório")
    private String state;

    @NotBlank(message = "O país é obrigatório")
    private String country;
}
