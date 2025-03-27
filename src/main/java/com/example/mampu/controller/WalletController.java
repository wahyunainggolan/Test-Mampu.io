package com.example.mampu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mampu.exception.InsufficientBalanceException;
import com.example.mampu.exception.UserNotFoundException;
import com.example.mampu.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam String username, @RequestParam double amount) {
        try {
            walletService.withdraw(username, amount);
            return ResponseEntity.ok().body("Withdrawal successful");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam String username) {
        try {
            double balance = walletService.getBalance(username);
            return ResponseEntity.ok().body("Current balance: " + balance);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
