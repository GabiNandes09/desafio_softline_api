package com.gabrielfernandes.Desafio_SoftLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielfernandes.Desafio_SoftLine.models.product.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{

}
