package com.techmanage.user_management.repository;

import com.techmanage.user_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface para operações no banco de dados
public interface UserRepository extends JpaRepository<User, Long> {
}
