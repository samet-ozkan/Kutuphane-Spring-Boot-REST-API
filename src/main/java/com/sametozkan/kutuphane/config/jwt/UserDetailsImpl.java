package com.sametozkan.kutuphane.config.jwt;

import com.sametozkan.kutuphane.entity.model.Account;
import com.sametozkan.kutuphane.util.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final String type;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password, String type, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Account account, Collection<? extends GrantedAuthority> authorities) {

        return new UserDetailsImpl(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.getType(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
