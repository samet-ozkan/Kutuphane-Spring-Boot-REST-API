package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.YazarReq;
import com.sametozkan.kutuphane.entity.dto.response.YazarRes;
import com.sametozkan.kutuphane.entity.model.Yazar;
import com.sametozkan.kutuphane.service.YazarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yazar")
@RequiredArgsConstructor
public class YazarController {

    private final YazarService yazarService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody YazarReq yazarReq) {
        yazarService.save(yazarReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<YazarRes> update(@PathVariable Long id, @RequestBody YazarReq yazarReq) {
        YazarRes yazarRes = yazarService.update(id, yazarReq);
        return new ResponseEntity<>(yazarRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<YazarRes>> findAll() {
        List<YazarRes> yazarResList = yazarService.findAll();
        return new ResponseEntity<>(yazarResList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public YazarRes findById(@PathVariable Long id) {
        return yazarService.findById(id);
    }
}
