package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapYazarReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapYazarRes;
import com.sametozkan.kutuphane.entity.model.KitapYazar;
import com.sametozkan.kutuphane.service.KitapYazarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-yazar")
@RequiredArgsConstructor
public class KitapYazarController {

    private final KitapYazarService kitapYazarService;

    @PostMapping
    public void save(@RequestBody KitapYazarReq kitapYazarReq) {
        kitapYazarService.save(kitapYazarReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody KitapYazarReq kitapYazarReq) {
        kitapYazarService.update(id, kitapYazarReq);
    }

    @GetMapping
    public List<KitapYazarRes> findAll() {
        return kitapYazarService.findAll();
    }

    @GetMapping("/{id}")
    public KitapYazarRes findById(@PathVariable Long id) {
        return kitapYazarService.findById(id);
    }
}
