package com.gabrielfernandes.Desafio_SoftLine.models.client;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressMapper;
import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;

public final class ClientMapper {
    private ClientMapper(){}

    public static ClientModel toEntity(ClientRequestDTO request, AddressModel address){
        ClientModel model = new ClientModel();

        model.setName(request.getName());
        model.setFantasyName(request.getFantasyName());
        model.setDocument(request.getDocument());
        model.setAddress(address);

        return model;
    }

    public static ClientResponseDTO toResponse(ClientModel model){
        return new ClientResponseDTO(
            model.getId(),
            model.getName(),
            model.getFantasyName(),
            model.getDocument(),
            AddressMapper.toResponse(model.getAddress())
        );
    }

    public static void update(ClientModel model, ClientRequestDTO request, AddressModel address){
        model.setName(request.getName());
        model.setFantasyName(request.getFantasyName());
        model.setDocument(request.getDocument());
        model.setAddress(address);
    }
}
