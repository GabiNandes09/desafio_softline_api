package com.gabrielfernandes.Desafio_SoftLine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressMapper;
import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;
import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientMapper;
import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientModel;
import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientRequestDTO;
import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientResponseDTO;
import com.gabrielfernandes.Desafio_SoftLine.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final AddressService addressService;

    @Transactional(readOnly = true)
    public ClientResponseDTO selectById(int id) {
        return ClientMapper.toResponse(selectEntityById(id));
    }

    @Transactional
    public ClientModel selectEntityById(int id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente com id " + id + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<ClientResponseDTO> selectAll() {
        List<ClientModel> response = clientRepository.findAll();

        return response.stream().map(ClientMapper::toResponse).toList();
    }

    @Transactional
    public ClientResponseDTO save(ClientRequestDTO request) {
        AddressModel addressSaved = addressService.saveEntity(
                AddressMapper.toEntity(request.getAddress()));

        ClientModel response = ClientMapper.toEntity(request, addressSaved);

        return ClientMapper.toResponse(saveEntity(response));
    }

    @Transactional
    public ClientModel saveEntity(ClientModel model) {
        return clientRepository.save(model);
    }

    @Transactional
    public ClientResponseDTO update(int id, ClientRequestDTO updatedClient) {
        ClientModel existinClient = selectEntityById(id);

        AddressModel addressUpdated = addressService.update(
                existinClient.getAddress().getId(), updatedClient.getAddress());

        ClientMapper.update(existinClient, updatedClient, addressUpdated);

        existinClient = saveEntity(existinClient);

        return ClientMapper.toResponse(existinClient);
    }

    @Transactional
    public ClientResponseDTO deleteById(int id) {
        ClientModel entity = selectEntityById(id);
        clientRepository.delete(entity);
        return ClientMapper.toResponse(entity);
    }
}
