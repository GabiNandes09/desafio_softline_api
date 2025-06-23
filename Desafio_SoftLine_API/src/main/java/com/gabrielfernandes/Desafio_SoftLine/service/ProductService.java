package com.gabrielfernandes.Desafio_SoftLine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielfernandes.Desafio_SoftLine.models.product.ProductMapper;
import com.gabrielfernandes.Desafio_SoftLine.models.product.ProductModel;
import com.gabrielfernandes.Desafio_SoftLine.models.product.ProductRequestDTO;
import com.gabrielfernandes.Desafio_SoftLine.models.product.ProductResponseDTO;
import com.gabrielfernandes.Desafio_SoftLine.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductResponseDTO selectById(int id) {
        ProductModel response = selectEntityById(id);
        return ProductMapper.toResponse(response);
    }

    @Transactional(readOnly = true)
    public ProductModel selectEntityById(int id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Produto com id " + id + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> selectAll() {
        List<ProductModel> response = productRepository.findAll();
        return response.stream().map(ProductMapper::toResponse).toList();
    }

    @Transactional
    public ProductResponseDTO save(ProductRequestDTO product) {
        ProductModel request = ProductMapper.toEntity(product);
        ProductModel response = saveEntity(request);
        return ProductMapper.toResponse(response);
    }

    @Transactional
    public ProductModel saveEntity(ProductModel model){
        return productRepository.save(model);
    }

    @Transactional
    public ProductResponseDTO update(int id, ProductRequestDTO updatedProduct) {

        ProductModel existingProduct = selectEntityById(id);

        ProductMapper.update(existingProduct, updatedProduct);

        existingProduct = saveEntity(existingProduct);

        return ProductMapper.toResponse(existingProduct);
    }

    @Transactional
    public ProductResponseDTO deleteById(int id){
        ProductModel entity = selectEntityById(id);
        productRepository.deleteById(id);
        return ProductMapper.toResponse(entity);
    }
}
