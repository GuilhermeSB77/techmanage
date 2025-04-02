package com.techmanage.user_management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome completo é obrigatório e não pode estar em branco.")
    @Column(nullable = false)
    private String fullName;

    @Email(message = "O e-mail deve ser válido.")
    @NotBlank(message = "O e-mail é obrigatório e não pode estar em branco.")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "\\+\\d{1,3} \\d{1,2} \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato internacional, por exemplo, +55 11 99999-9999.")
    @NotBlank(message = "O telefone é obrigatório e não pode estar em branco.")
    @Column(nullable = false)
    private String phone;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo de usuário é obrigatório.")
    @Column(nullable = false)
    private UserType userType;

    // Construtores, getters e setters...

    public User() {
    }

    public User(String fullName, String email, String phone, LocalDate birthDate, UserType userType) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}