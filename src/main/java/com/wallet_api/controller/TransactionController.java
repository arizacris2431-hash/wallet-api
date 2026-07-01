package com.wallet_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet_api.dto.TransactionRequest;
import com.wallet_api.dto.TransactionResponse;
import com.wallet_api.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction (@Valid @RequestBody TransactionRequest transactionRequest) {
        System.out.println("Entró al service");
        TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getMyTransactions () {
        List<TransactionResponse> transactionResponse = transactionService.getMyTransactions();
        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionsById (@PathVariable Long id) {
        TransactionResponse transactionResponse = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transactionResponse);
    }
}
