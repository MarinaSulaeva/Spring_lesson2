package ru.skypro.lessons.springboot.weblibrary_1.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "auth_user")
@Getter
@Setter
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private int enabled;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
