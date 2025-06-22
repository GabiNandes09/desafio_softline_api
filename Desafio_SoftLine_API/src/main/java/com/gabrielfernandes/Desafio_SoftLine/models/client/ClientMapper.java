package com.gabrielfernandes.Desafio_SoftLine.models.client;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;

public class ClientMapper {
    public static ClientModel toEntity(ClientRequestDTO dto, AddressModel address){
        return new ClientModel(
            dto.getName(),
            dto.getFantasyName(),
            dto.getDocument(),
            address
        );
    }
}
