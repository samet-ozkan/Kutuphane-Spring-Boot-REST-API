package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.YazarReq;
import com.sametozkan.kutuphane.entity.dto.response.YazarRes;
import com.sametozkan.kutuphane.entity.model.Yazar;
import com.sametozkan.kutuphane.service.YazarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yazar")
@RequiredArgsConstructor
public class YazarController {

    private final YazarService yazarService;

    @PostMapping
    public void save(@RequestBody YazarReq yazarReq) {
        yazarService.save(yazarReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody YazarReq yazarReq) {
        yazarService.update(id, yazarReq);
    }

    @GetMapping
    public List<YazarRes> findAll() {
        return yazarService.findAll();
    }

    @GetMapping("/{id}")
    public YazarRes findById(@PathVariable Long id) {
        return yazarService.findById(id);
    }
}
