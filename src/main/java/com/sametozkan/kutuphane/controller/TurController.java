package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.TurReq;
import com.sametozkan.kutuphane.entity.dto.response.TurRes;
import com.sametozkan.kutuphane.service.TurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tur")
@RequiredArgsConstructor
public class TurController {

    private final TurService turService;

    @PostMapping
    public void save(@RequestBody TurReq turReq) {
        turService.save(turReq);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody TurReq turReq) {
        turService.update(id, turReq);
    }

    @GetMapping
    public List<TurRes> findAll() {
        return turService.findAll();
    }

    @GetMapping("/{id}")
    public TurRes findById(@PathVariable Long id) {
        return turService.findById(id);
    }
}
