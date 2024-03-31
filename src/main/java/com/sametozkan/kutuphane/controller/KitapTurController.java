package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapTurReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapTurRes;
import com.sametozkan.kutuphane.service.KitapTurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-tur")
@RequiredArgsConstructor
public class KitapTurController {

    private final KitapTurService kitapTurService;

    @PostMapping
    public void save(@RequestBody KitapTurReq kitapTurReq) {
        kitapTurService.save(kitapTurReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody KitapTurReq kitapTurReq) {
        kitapTurService.update(id, kitapTurReq);
    }

    @GetMapping
    public List<KitapTurRes> findAll() {
        return kitapTurService.findAll();
    }

    @GetMapping("/{id}")
    public KitapTurRes findById(@PathVariable Long id) {
        return kitapTurService.findById(id);
    }
}
