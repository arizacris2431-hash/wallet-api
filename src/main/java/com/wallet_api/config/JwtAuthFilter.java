package com.wallet_api.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.wallet_api.service.JwtService;
import com.wallet_api.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserService userService;


    public JwtAuthFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        boolean isValid = jwtService.isTokenValid(token);

        if (!isValid) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtService.extractEmail(token);

        try {
            UserDetails loadEmail = userService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loadEmail,
                null,
                loadEmail.getAuthorities()
            );

            // SecurityContextHolder es el lugar donde Spring Security guarda temporalmente la información del usuario autenticado durante una petición, 
            // para que cualquier parte de la aplicación pueda saber quién está haciendo la operación.
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authToken);

            SecurityContextHolder.setContext(context);

        } catch (UsernameNotFoundException e) {
            filterChain.doFilter(request, response);
            return;
        }
        
        filterChain.doFilter(request, response);

    }

}
