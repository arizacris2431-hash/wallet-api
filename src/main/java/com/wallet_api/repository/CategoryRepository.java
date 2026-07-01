package com.wallet_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet_api.entity.Category;
import com.wallet_api.entity.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    // obtener todas las categorias del usuario
    List<Category> findByUser (User user);

    // obtener una categoria del usuario
    Optional<Category> findByIdAndUser (Long id, User user);
}
