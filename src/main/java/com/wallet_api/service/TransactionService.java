package com.wallet_api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.wallet_api.dto.TransactionRequest;
import com.wallet_api.dto.TransactionResponse;
import com.wallet_api.entity.Account;
import com.wallet_api.entity.Category;
import com.wallet_api.entity.Transaction;
import com.wallet_api.entity.TransactionType;
import com.wallet_api.entity.User;
import com.wallet_api.exception.AccountNotFoundException;
import com.wallet_api.exception.BadRequestException;
import com.wallet_api.exception.CategoryNotFoundException;
import com.wallet_api.exception.InsufficientBalanceException;
import com.wallet_api.exception.TransactionNotFoundException;
import com.wallet_api.repository.AccountRepository;
import com.wallet_api.repository.CategoryRepository;
import com.wallet_api.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    
    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository,
            AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public TransactionResponse createTransaction (TransactionRequest transactionRequest) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Account account = accountRepository.findByIdAndUser(transactionRequest.getAccountId(), user).orElseThrow(() -> new AccountNotFoundException("The account not found"));

        Category category = categoryRepository.findByIdAndUser(transactionRequest.getCategoryId(), user).orElseThrow(() -> new CategoryNotFoundException("The category not found"));

        if (transactionRequest.getType() == TransactionType.INCOME) {
            BigDecimal currentAmount = account.getBalance().add(transactionRequest.getAmount());
            account.setBalance(currentAmount);
        } else if (account.getBalance().compareTo(transactionRequest.getAmount()) >= 0) {
            BigDecimal currentAmount = account.getBalance().subtract(transactionRequest.getAmount());
            account.setBalance(currentAmount);
        } else {
                throw new InsufficientBalanceException("Insufficient balance to complete this transaction.");
        }
        
        Account updatedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();

        transaction.setDescription(transactionRequest.getDescription());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setType(transactionRequest.getType());
        transaction.setCategory(category);
        transaction.setAccount(account);
        transaction.setUser(user);
        
        Transaction saveTransaction = transactionRepository.save(transaction);

        return TransactionResponse.builder()
            .id(saveTransaction.getId())
            .description(saveTransaction.getDescription())
            .amount(saveTransaction.getAmount())
            .type(saveTransaction.getType())
            .createdAt(saveTransaction.getCreatedAt())
            .accountName(updatedAccount.getName())
            .categoryName(category.getName())
            .build();
    }


    public List<TransactionResponse> getMyTransactions () {
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Transaction> transactions = transactionRepository.findByUser(user);

        return transactions.stream().map(t -> new TransactionResponse(
            t.getId(),
            t.getDescription(),
            t.getAmount(),
            t.getType(),
            t.getCreatedAt(),
            t.getAccount().getName(),
            t.getCategory().getName()
        )).toList();

    }

    public TransactionResponse getTransactionById (Long id) {

        if (id == null || id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero.");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Transaction transaction = transactionRepository.findByIdAndUser(id, user).orElseThrow(() -> new TransactionNotFoundException("The transaction with that ID could not be found."));
        
        return TransactionResponse.builder()
            .id(transaction.getId())
            .description(transaction.getDescription())
            .amount(transaction.getAmount())
            .type(transaction.getType())
            .createdAt(transaction.getCreatedAt())
            .accountName(transaction.getAccount().getName())
            .categoryName(transaction.getCategory().getName())
            .build();
    }

}
