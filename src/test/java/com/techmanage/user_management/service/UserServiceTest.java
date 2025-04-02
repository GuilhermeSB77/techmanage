package com.techmanage.user_management.service;

import com.techmanage.user_management.exception.UserNotFoundException;
import com.techmanage.user_management.model.User;
import com.techmanage.user_management.model.UserType;
import com.techmanage.user_management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("Joao Lira", "jose_lira7@example.com", "+55 81 98423-9874", LocalDate.of(1999, 5, 12), UserType.EDITOR);
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(user.getFullName(), savedUser.getFullName());
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(user.getFullName(), foundUser.get().getFullName());
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        User updatedUser = userService.updateUser(1L, user);
        assertNotNull(updatedUser);
        assertEquals(user.getFullName(), updatedUser.getFullName());
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(any(User.class));
        userService.deleteUser(1L);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
    }
}