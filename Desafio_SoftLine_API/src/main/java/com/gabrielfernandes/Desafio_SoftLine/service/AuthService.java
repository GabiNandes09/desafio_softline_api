package com.gabrielfernandes.Desafio_SoftLine.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword());

            Authentication auteAuthentication = authenticationManager.authenticate(userAuth);

            UserDetailImpl userAuthenticated = (UserDetailImpl) auteAuthentication.getPrincipal();

            String token = jwtUtils.generationTokenFromUserDetailsImpl(userAuthenticated);

            AcessDTO acessDTO = new AcessDTO(token);

            return acessDTO;
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Usuário ou senha inválidos.");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return new AcessDTO("Acesso negado");
    }

}
