package ru.skypro.lessons.springboot.weblibrary_1.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Authorities implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String authority;
//    @ManyToOne
//    @JoinColumn(name = "auth_user_id")
//    private AuthUser user;

}
