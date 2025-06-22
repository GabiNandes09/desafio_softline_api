package com.gabrielfernandes.Desafio_SoftLine.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielfernandes.Desafio_SoftLine.models.product.ProductModel;
import com.gabrielfernandes.Desafio_SoftLine.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAll(){
        List<ProductModel> response = productService.selectAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> selectById(@PathVariable("id") int id){
        ProductModel response = productService.selectById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductModel> create(
        @RequestBody @Valid ProductModel product
    ) {
        ProductModel response = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(
        @PathVariable("id") int id,
        @RequestBody @Valid ProductModel product
    ) {
        ProductModel response = productService.update(id, product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
        @PathVariable("id") int id
    ) {
        productService.deleteById(id);
        return ResponseEntity.ok("Produto removido com sucesso");
    }
}
