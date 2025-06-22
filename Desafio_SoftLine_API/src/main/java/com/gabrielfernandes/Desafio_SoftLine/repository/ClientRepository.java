package com.gabrielfernandes.Desafio_SoftLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientModel;

public interface ClientRepository extends JpaRepository<ClientModel, Integer> {

    
}
