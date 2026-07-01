package com.wallet_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet_api.dto.AccountRequest;
import com.wallet_api.dto.AccountResponse;
import com.wallet_api.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount (@Valid @RequestBody AccountRequest accountRequest) {
        AccountResponse create = accountService.createAccount(accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getMyAccounts () {
        List<AccountResponse> getAResponse = accountService.getMyAccounts();
        return ResponseEntity.ok(getAResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById (@PathVariable Long id) {
        AccountResponse gAccountResponse = accountService.getAccountById(id);
        return ResponseEntity.ok(gAccountResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> putAccountById (@PathVariable Long id, @Valid @RequestBody AccountRequest accountRequest) {
        AccountResponse pAccountResponse = accountService.putAccountById(id, accountRequest);
        return ResponseEntity.ok(pAccountResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById (@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
