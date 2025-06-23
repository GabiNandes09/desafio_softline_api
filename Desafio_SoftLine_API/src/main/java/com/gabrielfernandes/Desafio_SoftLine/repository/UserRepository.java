package com.gabrielfernandes.Desafio_SoftLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielfernandes.Desafio_SoftLine.models.user.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
