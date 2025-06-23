package com.gabrielfernandes.Desafio_SoftLine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressMapper;
import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;
import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressRequestDTO;
import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressResponseDTO;
import com.gabrielfernandes.Desafio_SoftLine.repository.AddressRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public AddressResponseDTO selectById(int id) {
        return AddressMapper.toResponse(selectEntityById(id));
    }

    @Transactional
    public AddressModel selectEntityById(int id){
        return addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Endereço com id " + id + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<AddressResponseDTO> selectAll() {
        List<AddressModel> response = addressRepository.findAll();
        return response.stream().map(AddressMapper::toResponse).toList();
    }

    @Transactional
    public AddressResponseDTO save(AddressRequestDTO request) {
        AddressModel response = saveEntity(AddressMapper.toEntity(request));
        return AddressMapper.toResponse(response);
    }

    @Transactional
    public AddressModel saveEntity(AddressModel model){
        return addressRepository.save(model);
    }

    @Transactional
    public AddressResponseDTO update(int id, AddressRequestDTO updatedAddress) {
        AddressModel existingAddress = selectEntityById(id);

        AddressMapper.update(existingAddress, updatedAddress);
        
        existingAddress = saveEntity(existingAddress);

        return AddressMapper.toResponse(existingAddress);
    }

    @Transactional
    public AddressResponseDTO deleteById(int id){
        AddressModel entity = selectEntityById(id);
        addressRepository.deleteById(id);
        return AddressMapper.toResponse(entity);
    }
}
