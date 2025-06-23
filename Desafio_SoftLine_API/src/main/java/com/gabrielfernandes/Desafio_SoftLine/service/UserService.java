package com.gabrielfernandes.Desafio_SoftLine.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielfernandes.Desafio_SoftLine.models.user.UserMapper;
import com.gabrielfernandes.Desafio_SoftLine.models.user.UserModel;
import com.gabrielfernandes.Desafio_SoftLine.models.user.UserRequestDTO;
import com.gabrielfernandes.Desafio_SoftLine.models.user.UserResponseDTO;
import com.gabrielfernandes.Desafio_SoftLine.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserModel selectEntityById(int id){
        return userRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Usuário com id " + id + " não encontrado.")
        );
    }

    @Transactional(readOnly = true)
    public UserResponseDTO selectById(int id){
        return UserMapper.toResponse(selectEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> selectAll(){
        List<UserModel> response = userRepository.findAll();

        return response.stream().map(UserMapper::toResponse).toList();
    }

    @Transactional
    public UserResponseDTO save(UserRequestDTO request){
        UserModel response = saveEntity(UserMapper.toEntity(request));
        return UserMapper.toResponse(response);
    }

    @Transactional
    public UserModel saveEntity(UserModel model){
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        return userRepository.save(model);
    }

    @Transactional
    public UserResponseDTO update(int id, UserRequestDTO updatedUser){
        UserModel existingUser = selectEntityById(id);

        UserMapper.upgrade(existingUser, updatedUser);

        existingUser = saveEntity(existingUser);

        return UserMapper.toResponse(existingUser);
    }

    @Transactional
    public UserResponseDTO deleteById(int id){
        UserModel entity = selectEntityById(id);
        userRepository.deleteById(id);
        return UserMapper.toResponse(entity);
    }

}
