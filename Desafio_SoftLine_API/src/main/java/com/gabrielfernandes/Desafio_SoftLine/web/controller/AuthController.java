package com.gabrielfernandes.Desafio_SoftLine.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielfernandes.Desafio_SoftLine.models.user.AcessDTO;
import com.gabrielfernandes.Desafio_SoftLine.models.user.UserRequestDTO;
import com.gabrielfernandes.Desafio_SoftLine.service.AuthService;
import com.gabrielfernandes.Desafio_SoftLine.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AcessDTO> login(@RequestBody UserRequestDTO request) {
        AcessDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(userService.save(request));
    }

}
