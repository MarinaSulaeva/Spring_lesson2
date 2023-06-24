package ru.skypro.lessons.springboot.weblibrary_1.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUserPrincipal implements UserDetails {

    private String username;
    private String password;
    private List<SecurityGrantedAuthorities> authoritiesList;

    public SecurityUserPrincipal(AuthUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authoritiesList = List.of(user.getAuthorities()).stream()
                .map(SecurityGrantedAuthorities::new)
                .toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(authoritiesList);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
