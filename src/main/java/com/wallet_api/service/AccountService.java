package com.wallet_api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wallet_api.dto.AccountRequest;
import com.wallet_api.dto.AccountResponse;
import com.wallet_api.entity.Account;
import com.wallet_api.entity.User;
import com.wallet_api.exception.AccountNotFoundException;
import com.wallet_api.exception.BadRequestException;
import com.wallet_api.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount (AccountRequest accountRequest) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = new Account();
        
        account.setName(accountRequest.getName());
        account.setAccountType(accountRequest.getAccountType());
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);

        Account saveDB = accountRepository.save(account);

        return AccountResponse.builder()
            .id(saveDB.getId())
            .name(saveDB.getName())
            .balance(saveDB.getBalance())
            .accountType(saveDB.getAccountType())
            .createdAt(saveDB.getCreatedAt())
            .build();
    }

    public List<AccountResponse> getMyAccounts () {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Account> aList = accountRepository.findByUser(user);
        
        return aList.stream().map(a -> new AccountResponse(
            a.getId(),
            a.getName(),
            a.getBalance(),
            a.getAccountType(),
            a.getCreatedAt()

        )).toList();
    }

    public AccountResponse getAccountById (Long id) {

        if (id == null || id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Account account = accountRepository.findByIdAndUser(id, user).orElseThrow(() -> new AccountNotFoundException("The account not found"));

        return AccountResponse.builder()
            .id(account.getId())
            .name(account.getName())
            .accountType(account.getAccountType())
            .balance(account.getBalance())
            .createdAt(account.getCreatedAt())
            .build();

    }

    public AccountResponse putAccountById (Long id, AccountRequest accountRequest) {

        if (id == null || id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = accountRepository.findByIdAndUser(id, user).orElseThrow(() -> new AccountNotFoundException("The account not found"));

        account.setName(accountRequest.getName());
        account.setAccountType(accountRequest.getAccountType());

        Account saveDB = accountRepository.save(account);

        return AccountResponse.builder()
            .id(saveDB.getId())
            .name(saveDB.getName())
            .accountType(saveDB.getAccountType())
            .balance(saveDB.getBalance())
            .createdAt(saveDB.getCreatedAt())
            .build();
    }

    public void deleteAccountById (Long id) {
        if (id == null || id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equals to Zero");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = accountRepository.findByIdAndUser(id, user).orElseThrow(() -> new AccountNotFoundException("The account not found"));

        accountRepository.deleteById(account.getId());
    }


}
