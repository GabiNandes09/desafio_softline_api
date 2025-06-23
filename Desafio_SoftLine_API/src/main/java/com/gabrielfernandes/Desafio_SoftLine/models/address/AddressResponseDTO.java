package com.gabrielfernandes.Desafio_SoftLine.models.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AddressResponseDTO {
    private String zipCode;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
}
