package com.wallet_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet_api.entity.Transaction;
import com.wallet_api.entity.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    // todas las transacciones del usuario autenticado
    List<Transaction > findByUser (User user);

    // Busca una transacción por su ID, pero solo si le pertenece al usuario actual, devolviendo una caja segura (Optional) que evita errores si no existe nada
    Optional<Transaction> findByIdAndUser (Long id, User user);
}
