package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.service.KitapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap")
@RequiredArgsConstructor
public class KitapController {

    private final KitapService kitapService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody KitapReq kitapReq) {
        kitapService.save(kitapReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitapRes> update(@PathVariable Long id, @RequestBody KitapReq kitapReq) {
        KitapRes kitapRes = kitapService.update(id, kitapReq);
        return new ResponseEntity<>(kitapRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<KitapRes>> findAll() {
        List<KitapRes> kitapResList = kitapService.findAll();
        return new ResponseEntity<>(kitapResList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitapRes> findById(@PathVariable Long id) {
        KitapRes kitapRes = kitapService.findById(id);
        return new ResponseEntity<>(kitapRes, HttpStatus.OK);
    }
}
