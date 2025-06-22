package com.gabrielfernandes.Desafio_SoftLine.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielfernandes.Desafio_SoftLine.service.AddressService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;
}
