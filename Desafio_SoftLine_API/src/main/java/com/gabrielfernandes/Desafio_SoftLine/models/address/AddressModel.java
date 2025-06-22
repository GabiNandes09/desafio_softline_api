package com.gabrielfernandes.Desafio_SoftLine.models.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "addresses")
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message = "O CEP é obrigatório")
    @Size(min = 8, max = 8, message = "O CEP deve ter exatamente 8 caracteres")
    @Column(nullable = false, length = 8)
    String zipCode;

    @NotBlank(message = "A rua é obrigatória")
    @Column(nullable = false)
    String street;

    @NotBlank(message = "O número é obrigatório")
    @Column(nullable = false)
    String number;

    @NotBlank(message = "O bairro é obrigatório")
    @Column(nullable = false)
    String neighborhood;

    @NotBlank(message = "A cidade é obrigatória")
    @Column(nullable = false)
    String city;

    @NotBlank(message = "O estado é obrigatório")
    @Column(nullable = false)
    String state;

    @NotBlank(message = "O país é obrigatório")
    @Column(nullable = false)
    String country;
}
