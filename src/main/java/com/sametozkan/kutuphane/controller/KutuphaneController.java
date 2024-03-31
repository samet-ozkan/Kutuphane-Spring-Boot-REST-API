package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneRes;
import com.sametozkan.kutuphane.service.KutuphaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/kutuphane")
@RequiredArgsConstructor
public class KutuphaneController {

    private final KutuphaneService kutuphaneService;

    @PostMapping
    public void save(@RequestBody KutuphaneReq kutuphaneReq) {
        kutuphaneService.save(kutuphaneReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody KutuphaneReq kutuphaneReq) {
        kutuphaneService.update(id, kutuphaneReq);
    }

    @GetMapping
    public List<KutuphaneRes> findAll() {
        return kutuphaneService.findAll();
    }

    @GetMapping("/{id}")
    public KutuphaneRes findById(@PathVariable Long id) {
        return kutuphaneService.findById(id);
    }
}
