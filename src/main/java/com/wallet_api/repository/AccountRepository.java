package com.wallet_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet_api.entity.Account;
import com.wallet_api.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long>{
    List<Account> findByUser (User user);
    
    Optional<Account> findByIdAndUser (Long id, User user);
}
