package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.service.KitapKullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-kullanici")
@RequiredArgsConstructor
public class KitapKullaniciController {

    private final KitapKullaniciService kitapKullaniciService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody KitapKullaniciReq kitapKullaniciReq) {
        kitapKullaniciService.save(kitapKullaniciReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitapKullaniciRes> update(@PathVariable Long id, @RequestBody KitapKullaniciReq kitapKullaniciReq) {
        KitapKullaniciRes kitapKullaniciRes = kitapKullaniciService.update(id, kitapKullaniciReq);
        return new ResponseEntity<>(kitapKullaniciRes, HttpStatus.OK);
    }

    @GetMapping
    public List<KitapKullaniciRes> findAll() {
        return kitapKullaniciService.findAll();
    }

    @GetMapping("/{id}")
    public KitapKullaniciRes findById(@PathVariable Long id) {
        return kitapKullaniciService.findById(id);
    }
}
