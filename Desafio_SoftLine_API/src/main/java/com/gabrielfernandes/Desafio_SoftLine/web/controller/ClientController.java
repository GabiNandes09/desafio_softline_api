package com.gabrielfernandes.Desafio_SoftLine.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielfernandes.Desafio_SoftLine.service.ClientService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
}
