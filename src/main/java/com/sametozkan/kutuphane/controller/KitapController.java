package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.service.KitapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap")
@RequiredArgsConstructor
public class KitapController {

    private final KitapService kitapService;

    @PostMapping
    public void save(@RequestBody KitapReq kitapReq) {
        kitapService.save(kitapReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody KitapReq kitapReq) {
        kitapService.update(id, kitapReq);
    }

    @GetMapping
    public List<KitapRes> findAll() {
        return kitapService.findAll();
    }

    @GetMapping("/{id}")
    public KitapRes findById(@PathVariable Long id) {
        return kitapService.findById(id);
    }
}
