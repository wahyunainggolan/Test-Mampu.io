package com.example.mampu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mampu.exception.InsufficientBalanceException;
import com.example.mampu.exception.UserNotFoundException;
import com.example.mampu.model.User;
import com.example.mampu.model.Wallet;
import com.example.mampu.repository.UserRepository;
import com.example.mampu.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void withdraw(String username, double amount) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        
        Wallet wallet = walletRepository.findByUserId(user.getId());
        if (wallet == null) {
            throw new UserNotFoundException("Wallet not found for user: " + username);
        }
        
        if (wallet.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
        
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }

    public double getBalance(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        
        Wallet wallet = walletRepository.findByUserId(user.getId());
        if (wallet == null) {
            throw new UserNotFoundException("Wallet not found for user: " + username);
        }
        
        return wallet.getBalance();
    }
}
