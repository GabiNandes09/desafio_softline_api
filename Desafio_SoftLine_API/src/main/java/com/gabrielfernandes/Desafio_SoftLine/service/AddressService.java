package com.gabrielfernandes.Desafio_SoftLine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;
import com.gabrielfernandes.Desafio_SoftLine.repository.AddressRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public AddressModel selectById(int id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Endereço com id " + id + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<AddressModel> selectAll() {
        return addressRepository.findAll();
    }

    @Transactional
    public AddressModel save(AddressModel address) {
        return addressRepository.save(address);
    }

    @Transactional
    public AddressModel update(int id, AddressModel updatedAddress) {
        AddressModel existingAddress = selectById(id);

        existingAddress.setZipCode(updatedAddress.getZipCode());
        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setNumber(updatedAddress.getNumber());
        existingAddress.setNeighborhood(updatedAddress.getNeighborhood());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setCountry(updatedAddress.getCountry());
        

        return save(existingAddress);
    }

    @Transactional
    public void deleteById(int id){
        addressRepository.deleteById(id);
    }
}
