package com.gabrielfernandes.Desafio_SoftLine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielfernandes.Desafio_SoftLine.models.product.ProductModel;
import com.gabrielfernandes.Desafio_SoftLine.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductModel selectById(int id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Produto com id " + id + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<ProductModel> selectAll() {
        return productRepository.findAll();
    }

    @Transactional
    public ProductModel save(ProductModel product) {
        return productRepository.save(product);
    }

    @Transactional
    public ProductModel update(int id, ProductModel updatedProduct) {
        ProductModel existingProduct = selectById(id);

        existingProduct.setCode(updatedProduct.getCode());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setBarCode(updatedProduct.getBarCode());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setGrossWeight(updatedProduct.getGrossWeight());
        existingProduct.setNetWeight(updatedProduct.getNetWeight());

        return save(existingProduct);
    }

    @Transactional
    public void deleteById(int id){
        productRepository.deleteById(id);
    }
}
