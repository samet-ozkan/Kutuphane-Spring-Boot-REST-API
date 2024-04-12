package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KullaniciRegisterReq;
import com.sametozkan.kutuphane.entity.dto.request.KutuphaneRegisterReq;
import com.sametozkan.kutuphane.entity.dto.request.LoginReq;
import com.sametozkan.kutuphane.entity.dto.response.JwtRes;
import com.sametozkan.kutuphane.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtRes> login(@RequestBody LoginReq loginReq) {
        JwtRes jwtRes = authService.login(loginReq);
        return new ResponseEntity<>(jwtRes, HttpStatus.OK);
    }

    @PostMapping("/register/kutuphane")
    public ResponseEntity<Void> registerKutuphane(@RequestBody KutuphaneRegisterReq kutuphaneRegisterReq) {
        authService.registerKutuphane(kutuphaneRegisterReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register/kullanici")
    public ResponseEntity<Void> registerKullanici(@RequestBody KullaniciRegisterReq kullaniciRegisterReq) {
        authService.registerKullanici(kullaniciRegisterReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
