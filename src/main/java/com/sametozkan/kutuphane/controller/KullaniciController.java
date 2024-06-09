package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KullaniciRes;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneRes;
import com.sametozkan.kutuphane.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kullanici")
@RequiredArgsConstructor
public class KullaniciController {

    private final KullaniciService kullaniciService;

    @PutMapping("/{id}")
    public ResponseEntity<KullaniciRes> update(@PathVariable Long id, @RequestBody KullaniciReq kullaniciReq) {
        KullaniciRes kullaniciRes = kullaniciService.update(id, kullaniciReq);
        return new ResponseEntity<>(kullaniciRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<KullaniciRes>> findAll() {
        List<KullaniciRes> kullaniciResList = kullaniciService.findAll();
        return new ResponseEntity<>(kullaniciResList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KullaniciRes> findById(@PathVariable Long id) {
        KullaniciRes kullaniciRes = kullaniciService.findById(id);
        return new ResponseEntity<>(kullaniciRes, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<KullaniciRes> findByAccountId(@PathVariable Long accountId){
        KullaniciRes kullaniciRes = kullaniciService.findByAccountId(accountId);
        return new ResponseEntity<>(kullaniciRes, HttpStatus.OK);
    }
}
