package com.gabrielfernandes.Desafio_SoftLine.service;

import org.springframework.stereotype.Service;

import com.gabrielfernandes.Desafio_SoftLine.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
}
