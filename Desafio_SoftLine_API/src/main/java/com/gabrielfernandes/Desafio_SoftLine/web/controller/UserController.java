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

import com.gabrielfernandes.Desafio_SoftLine.models.user.UserRequestDTO;
import com.gabrielfernandes.Desafio_SoftLine.models.user.UserResponseDTO;
import com.gabrielfernandes.Desafio_SoftLine.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> response = userService.selectAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> selectById(@PathVariable("id") int id){
        UserResponseDTO response = userService.selectById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
        @RequestBody @Valid UserRequestDTO user
    ) {
        UserResponseDTO response = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
        @PathVariable("id") int id,
        @RequestBody @Valid UserRequestDTO user
    ) {
        UserResponseDTO response = userService.update(id, user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteById(
        @PathVariable("id") int id
    ) {
        UserResponseDTO response = userService.deleteById(id);
        return ResponseEntity.ok(response);
    }
}
