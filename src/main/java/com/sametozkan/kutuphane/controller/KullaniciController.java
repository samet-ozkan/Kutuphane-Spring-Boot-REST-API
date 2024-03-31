package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KullaniciRes;
import com.sametozkan.kutuphane.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kullanici")
@RequiredArgsConstructor
public class KullaniciController {

    private final KullaniciService kullaniciService;

    @PostMapping
    public void save(@RequestBody KullaniciReq kullaniciReq) {
        kullaniciService.save(kullaniciReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody KullaniciReq kullaniciReq) {
        kullaniciService.update(id, kullaniciReq);
    }

    @GetMapping
    public List<KullaniciRes> findAll() {
        return kullaniciService.findAll();
    }

    @GetMapping("/{id}")
    public KullaniciRes findById(@PathVariable Long id) {
        return kullaniciService.findById(id);
    }
}
