package com.sametozkan.kutuphane.config.jwt;

import com.sametozkan.kutuphane.entity.model.Account;
import com.sametozkan.kutuphane.service.AccountService;
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
        if(Objects.equals(type, "kullanici")){
            authorities.add(new SimpleGrantedAuthority("ROLE_KULLANICI"));
        }
        else{
            authorities.add(new SimpleGrantedAuthority("ROLE_KUTUPHANE"));
        }

        return UserDetailsImpl.build(account, authorities);
    }
}
