package com.gabrielfernandes.Desafio_SoftLine.service;

import org.springframework.stereotype.Service;

import com.gabrielfernandes.Desafio_SoftLine.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
}
