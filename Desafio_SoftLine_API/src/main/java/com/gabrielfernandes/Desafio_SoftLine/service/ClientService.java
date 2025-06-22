package com.gabrielfernandes.Desafio_SoftLine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;
import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientModel;
import com.gabrielfernandes.Desafio_SoftLine.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final AddressService addressService;

    @Transactional(readOnly = true)
    public ClientModel selectById(int id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente com id " + id + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<ClientModel> selectAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public ClientModel save(ClientModel client) {

        AddressModel addressSaved = addressService.save(client.getAddress());

        client.setAddress(addressSaved);

        return clientRepository.save(client);
    }

    @Transactional
    public ClientModel update(int id, ClientModel updatedClient) {
        ClientModel existingClient = selectById(id);

        existingClient.setName(updatedClient.getName());
        existingClient.setFantasyName(updatedClient.getFantasyName());
        existingClient.setDocument(updatedClient.getDocument());

        AddressModel existinAddress = addressService.update(updatedClient.getAddress().getId(),
                updatedClient.getAddress());

        existingClient.setAddress(existinAddress);

        return save(existingClient);
    }

    @Transactional
    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }
}
