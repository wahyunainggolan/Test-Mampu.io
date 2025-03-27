package com.example.mampu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mampu.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}