package com.sametozkan.kutuphane.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sametozkan.kutuphane.config.jwt.JwtUtils;
import com.sametozkan.kutuphane.config.jwt.UserDetailsImpl;
import com.sametozkan.kutuphane.entity.dto.request.*;
import com.sametozkan.kutuphane.entity.dto.response.JwtRes;
import com.sametozkan.kutuphane.entity.model.Account;
import com.sametozkan.kutuphane.entity.model.RefreshToken;
import com.sametozkan.kutuphane.exception.WrongAccountTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final RefreshTokenService refreshTokenService;

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

    public JwtRes login(LoginReq loginReq) throws WrongAccountTypeException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if(!userDetails.getType().equals(loginReq.getAccountType()))
            throw new WrongAccountTypeException("Hesap türü uyumsuz.");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return JwtRes.builder()
                .jwt(jwt)
                .refreshToken(refreshToken.getToken())
                .refreshExpiryDate(refreshToken.getExpiryDate().toEpochMilli())
                .accountId(userDetails.getId())
                .accountType(userDetails.getType())
                .email(userDetails.getUsername())
                .build();
    }

}
