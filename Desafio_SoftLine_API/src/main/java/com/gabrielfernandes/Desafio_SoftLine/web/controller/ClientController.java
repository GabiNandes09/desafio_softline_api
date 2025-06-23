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

import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientRequestDTO;
import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientResponseDTO;
import com.gabrielfernandes.Desafio_SoftLine.service.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAll(){
        List<ClientResponseDTO> response = clientService.selectAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> selectById(@PathVariable("id") int id){
        ClientResponseDTO response = clientService.selectById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(
        @RequestBody @Valid ClientRequestDTO client
    ) {
        ClientResponseDTO response = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(
        @PathVariable("id") int id,
        @RequestBody @Valid ClientRequestDTO client
    ) {
        ClientResponseDTO response = clientService.update(id, client);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> deleteById(
        @PathVariable("id") int id
    ) {
        ClientResponseDTO response = clientService.deleteById(id);
        return ResponseEntity.ok(response);
    }
}
