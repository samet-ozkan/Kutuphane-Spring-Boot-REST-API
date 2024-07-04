package com.sametozkan.kutuphane.config.jwt;

import com.sametozkan.kutuphane.entity.model.Account;
import com.sametozkan.kutuphane.service.AccountService;
import com.sametozkan.kutuphane.util.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        String type = account.getType();
        System.out.println("Type: " + type);
        if (Objects.equals(type, AccountType.KULLANICI.getValue())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_KULLANICI"));
        } else if (Objects.equals(type, AccountType.KUTUPHANE.getValue())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_KUTUPHANE"));
        }
        else if(Objects.equals(type, AccountType.ADMIN.getValue())){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return UserDetailsImpl.build(account, authorities);
    }
}
