package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KullaniciRegisterReq;
import com.sametozkan.kutuphane.entity.dto.request.KutuphaneRegisterReq;
import com.sametozkan.kutuphane.entity.dto.request.LoginReq;
import com.sametozkan.kutuphane.entity.dto.response.JwtRes;
import com.sametozkan.kutuphane.service.AuthService;
import lombok.RequiredArgsConstructor;
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
    public JwtRes login(@RequestBody LoginReq loginReq) {
        System.out.println(loginReq);
        return authService.login(loginReq);
    }

    @PostMapping("/register/kutuphane")
    public void registerKutuphane(@RequestBody KutuphaneRegisterReq kutuphaneRegisterReq) {
        authService.registerKutuphane(kutuphaneRegisterReq);
    }

    @PostMapping("/register/kullanici")
    public void registerKullanici(@RequestBody KullaniciRegisterReq kullaniciRegisterReq) {
        authService.registerKullanici(kullaniciRegisterReq);
    }
}
