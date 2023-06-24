package ru.skypro.lessons.springboot.weblibrary_1.security;

import jakarta.persistence.*;
import lombok.Getter;
import ru.skypro.lessons.springboot.weblibrary_1.security.Authorities;

import java.util.List;


@Entity
@Table(name = "auth_user")
@Getter
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    @JoinColumn(name = "authorities_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Authorities authorities;

}
