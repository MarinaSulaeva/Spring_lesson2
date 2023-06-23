package ru.skypro.lessons.springboot.weblibrary_1.security;

import org.springframework.security.core.GrantedAuthority;

public class SecurityGrantedAuthorities implements GrantedAuthority {

    private String role;

    public SecurityGrantedAuthorities(Authorities authorities) {
        this.role = authorities.getAuthority();
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
