package com.gabrielfernandes.Desafio_SoftLine.models.client;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class ClientResponseDTO {
    private int id;
    private String name;
    private String fantasyName;
    private String document;
    private AddressResponseDTO address;
}
