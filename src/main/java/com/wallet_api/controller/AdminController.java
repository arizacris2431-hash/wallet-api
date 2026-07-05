package com.wallet_api.controller;

import com.wallet_api.dto.UpdateUserRequest;
import com.wallet_api.dto.UserResponse;
import com.wallet_api.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// "Esta clase recibirá peticiones HTTP y devolverá respuestas."
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test () {
        return ResponseEntity.ok("test correctamente");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers () {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUser (@PathVariable Long id) {
        return ResponseEntity.ok(adminService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser (@PathVariable Long id, @Valid @RequestBody UpdateUserRequest userRequest) {
        return ResponseEntity.ok(adminService.updateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
