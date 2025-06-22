package com.gabrielfernandes.Desafio_SoftLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {

    
}
