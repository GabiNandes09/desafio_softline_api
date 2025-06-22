package com.gabrielfernandes.Desafio_SoftLine.service;

import org.springframework.stereotype.Service;

import com.gabrielfernandes.Desafio_SoftLine.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
}
