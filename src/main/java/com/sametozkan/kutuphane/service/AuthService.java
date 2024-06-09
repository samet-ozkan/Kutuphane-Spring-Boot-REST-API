package com.sametozkan.kutuphane.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sametozkan.kutuphane.config.jwt.JwtUtils;
import com.sametozkan.kutuphane.config.jwt.UserDetailsImpl;
import com.sametozkan.kutuphane.entity.dto.request.*;
import com.sametozkan.kutuphane.entity.dto.response.JwtRes;
import com.sametozkan.kutuphane.entity.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AccountService accountService;
    private final KutuphaneService kutuphaneService;
    private final KullaniciService kullaniciService;

    @Transactional
    public void registerKutuphane(KutuphaneRegisterReq kutuphaneRegisterReq) throws JsonProcessingException {
        Account account = accountService.save(kutuphaneRegisterReq.getAccount());
        kutuphaneService.save(kutuphaneRegisterReq.getKutuphane(), account);
    }

    @Transactional
    public void registerKullanici(KullaniciRegisterReq kullaniciRegisterReq) {
        Account account = accountService.save(kullaniciRegisterReq.getAccount());
        kullaniciService.save(kullaniciRegisterReq.getKullanici(), account);
    }

    public JwtRes login(LoginReq loginReq) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String jwt = jwtUtils.generateJwtToken(authentication);
            return JwtRes.builder()
                    .jwt(jwt)
                    .accountId(userDetails.getId())
                    .accountType(userDetails.getType())
                    .email(userDetails.getUsername())
                    .build();
        } catch (AuthenticationException e) {
            // Kimlik doğrulama hatası, uygun şekilde işleyin.
            e.printStackTrace(); // Hata mesajını yazdırın veya uygun şekilde işleyin.
            throw e;
        }
    }

}
