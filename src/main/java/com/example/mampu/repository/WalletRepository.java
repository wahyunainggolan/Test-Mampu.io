package com.example.mampu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mampu.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUserId(Long userId);
    
    @Query("UPDATE Wallet w SET w.balance = w.balance - :amount WHERE w.user.id = :userId AND w.balance >= :amount")
    int withdraw(@Param("userId") Long userId, @Param("amount") double amount);
}