package com.techmanage.user_management.controller;

import com.techmanage.user_management.exception.UserNotFoundException;
import com.techmanage.user_management.model.User;
import com.techmanage.user_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para obter todos os usuários
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Endpoint para criar um novo usuário
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    // Endpoint para obter um usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Lança uma exceção se o usuário não for encontrado
        User user = userService.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        return ResponseEntity.ok(user);
    }

    // Endpoint para atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        // Atualiza os detalhes do usuário
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint para excluir um usuário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // Exclui o usuário se ele existir
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}