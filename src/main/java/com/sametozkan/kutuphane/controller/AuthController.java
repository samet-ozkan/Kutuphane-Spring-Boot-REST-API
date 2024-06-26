package com.sametozkan.kutuphane.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sametozkan.kutuphane.entity.dto.request.KullaniciRegisterReq;
import com.sametozkan.kutuphane.entity.dto.request.KutuphaneRegisterReq;
import com.sametozkan.kutuphane.entity.dto.request.LoginReq;
import com.sametozkan.kutuphane.entity.dto.request.TokenRefreshReq;
import com.sametozkan.kutuphane.entity.dto.response.JwtRes;
import com.sametozkan.kutuphane.entity.dto.response.TokenRefreshRes;
import com.sametozkan.kutuphane.exception.WrongAccountTypeException;
import com.sametozkan.kutuphane.service.AuthService;
import com.sametozkan.kutuphane.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtRes> login(@RequestBody LoginReq loginReq) throws WrongAccountTypeException {
        JwtRes jwtRes = authService.login(loginReq);
        return new ResponseEntity<>(jwtRes, HttpStatus.OK);
    }

    @PostMapping("/register/kutuphane")
    public ResponseEntity<Void> registerKutuphane(@RequestBody KutuphaneRegisterReq kutuphaneRegisterReq) throws JsonProcessingException {
        authService.registerKutuphane(kutuphaneRegisterReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register/kullanici")
    public ResponseEntity<Void> registerKullanici(@RequestBody KullaniciRegisterReq kullaniciRegisterReq) {
        authService.registerKullanici(kullaniciRegisterReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenRefreshRes> refreshToken(@RequestBody TokenRefreshReq tokenRefreshReq) throws Exception {
        TokenRefreshRes tokenRefreshRes = refreshTokenService.refreshToken(tokenRefreshReq);
        return new ResponseEntity<>(tokenRefreshRes, HttpStatus.OK);
    }
}
