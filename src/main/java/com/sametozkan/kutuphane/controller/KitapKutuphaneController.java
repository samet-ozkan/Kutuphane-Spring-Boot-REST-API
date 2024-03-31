package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapKutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKutuphaneRes;
import com.sametozkan.kutuphane.service.KitapKutuphaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-kutuphane")
@RequiredArgsConstructor
public class KitapKutuphaneController {

    private final KitapKutuphaneService kitapKutuphaneService;

    @PostMapping
    public void save(@RequestBody KitapKutuphaneReq kitapKutuphaneReq) {
        kitapKutuphaneService.save(kitapKutuphaneReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody KitapKutuphaneReq kitapKutuphaneReq) {
        kitapKutuphaneService.update(id, kitapKutuphaneReq);
    }

    @GetMapping
    public List<KitapKutuphaneRes> findAll() {
        return kitapKutuphaneService.findAll();
    }

    @GetMapping("/{id}")
    public KitapKutuphaneRes findById(@PathVariable Long id) {
        return kitapKutuphaneService.findById(id);
    }
}
