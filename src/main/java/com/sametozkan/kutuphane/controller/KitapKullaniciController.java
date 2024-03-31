package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.service.KitapKullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-kullanici")
@RequiredArgsConstructor
public class KitapKullaniciController {

    private final KitapKullaniciService kitapKullaniciService;

    @PostMapping
    public void save(@RequestBody KitapKullaniciReq kitapKullaniciReq) {
        kitapKullaniciService.save(kitapKullaniciReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody KitapKullaniciReq kitapKullaniciReq) {
        kitapKullaniciService.update(id, kitapKullaniciReq);
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
