package com.gabrielfernandes.Desafio_SoftLine.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gabrielfernandes.Desafio_SoftLine.models.user.AcessDTO;
import com.gabrielfernandes.Desafio_SoftLine.models.user.UserRequestDTO;
import com.gabrielfernandes.Desafio_SoftLine.security.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AcessDTO login(UserRequestDTO request) {
        try {
            UsernamePasswordAuthenticationToken authInput = new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword());

            Authentication auth = authenticationManager.authenticate(authInput);

            UserDetailImpl user = (UserDetailImpl) auth.getPrincipal();
            String token = jwtUtils.generationTokenFromUserDetailsImpl(user);

            return new AcessDTO(token);

        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao realizar login.");
        }
    }

}
