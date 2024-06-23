package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapYazarReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapYazarRes;
import com.sametozkan.kutuphane.entity.model.KitapYazar;
import com.sametozkan.kutuphane.service.KitapYazarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-yazar")
@RequiredArgsConstructor
public class KitapYazarController {

    private final KitapYazarService kitapYazarService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody KitapYazarReq kitapYazarReq) {
        kitapYazarService.save(kitapYazarReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PutMapping("/{id}")
    public ResponseEntity<KitapYazarRes> update(@PathVariable Long id, @RequestBody KitapYazarReq kitapYazarReq) {
        KitapYazarRes kitapYazarRes = kitapYazarService.update(id, kitapYazarReq);
        return new ResponseEntity<>(kitapYazarRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<KitapYazarRes>> findAll() {
        List<KitapYazarRes> kitapYazarResList = kitapYazarService.findAll();
        return new ResponseEntity<>(kitapYazarResList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitapYazarRes> findById(@PathVariable Long id) {
        KitapYazarRes kitapYazarRes = kitapYazarService.findById(id);
        return new ResponseEntity<>(kitapYazarRes, HttpStatus.OK);
    }
}
