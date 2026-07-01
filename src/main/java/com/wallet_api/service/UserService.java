package com.wallet_api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wallet_api.entity.User;
import com.wallet_api.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    // "Ve a la base de datos, busca si existe alguien con este correo. Si no existe, échalo; si sí existe, pásame su información para revisar si su contraseña coincide".
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean existsByEmail (String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User save (User user) {
        return userRepository.save(user);
    }
}


