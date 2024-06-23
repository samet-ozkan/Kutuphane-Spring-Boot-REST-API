package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapKutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKutuphaneRes;
import com.sametozkan.kutuphane.service.KitapKutuphaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-kutuphane")
@RequiredArgsConstructor
public class KitapKutuphaneController {

    private final KitapKutuphaneService kitapKutuphaneService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody KitapKutuphaneReq kitapKutuphaneReq) {
        kitapKutuphaneService.save(kitapKutuphaneReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PutMapping("/{id}")
    public ResponseEntity<KitapKutuphaneRes> update(@PathVariable Long id, @RequestBody KitapKutuphaneReq kitapKutuphaneReq) {
        KitapKutuphaneRes kitapKutuphaneRes = kitapKutuphaneService.update(id, kitapKutuphaneReq);
        return new ResponseEntity<>(kitapKutuphaneRes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<KitapKutuphaneRes>> findAll() {
        List<KitapKutuphaneRes> kitapKutuphaneResList = kitapKutuphaneService.findAll();
        return new ResponseEntity<>(kitapKutuphaneResList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<KitapKutuphaneRes> findById(@PathVariable Long id) {
        KitapKutuphaneRes kitapKutuphaneRes = kitapKutuphaneService.findById(id);
        return new ResponseEntity<>(kitapKutuphaneRes, HttpStatus.OK);
    }
}
