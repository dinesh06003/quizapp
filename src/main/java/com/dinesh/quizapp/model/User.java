package com.dinesh.quizapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Email ID cannot be blank")
    @Email(message = "Email ID must be valid")
    private String emailId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "ADMIN|USER", message = "Role must be either ADMIN or USER")
    @Column(nullable = false)
    private String role = "USER";
}
