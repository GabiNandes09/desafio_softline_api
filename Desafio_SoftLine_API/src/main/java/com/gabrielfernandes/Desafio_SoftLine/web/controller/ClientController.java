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

import com.gabrielfernandes.Desafio_SoftLine.models.client.ClientModel;
import com.gabrielfernandes.Desafio_SoftLine.service.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientModel>> getAll(){
        List<ClientModel> response = clientService.selectAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> selectById(@PathVariable int id){
        ClientModel response = clientService.selectById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ClientModel> create(
        @RequestBody @Valid ClientModel client
    ) {
        ClientModel response = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientModel> update(
        @PathVariable int id,
        @RequestBody @Valid ClientModel client
    ) {
        ClientModel response = clientService.update(id, client);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
        @PathVariable int id
    ) {
        clientService.deleteById(id);
        return ResponseEntity.ok("Cliente removido com sucesso");
    }
}
