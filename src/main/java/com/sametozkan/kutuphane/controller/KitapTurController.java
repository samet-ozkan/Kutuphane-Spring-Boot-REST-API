package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KitapTurReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapTurRes;
import com.sametozkan.kutuphane.service.KitapTurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-tur")
@RequiredArgsConstructor
public class KitapTurController {

    private final KitapTurService kitapTurService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody KitapTurReq kitapTurReq) {
        kitapTurService.save(kitapTurReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PutMapping("/{id}")
    public ResponseEntity<KitapTurRes> update(@PathVariable Long id, @RequestBody KitapTurReq kitapTurReq) {
        KitapTurRes kitapTurRes = kitapTurService.update(id, kitapTurReq);
        return new ResponseEntity<>(kitapTurRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<KitapTurRes>> findAll() {
        List<KitapTurRes> kitapTurRes = kitapTurService.findAll();
        return new ResponseEntity<>(kitapTurRes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitapTurRes> findById(@PathVariable Long id) {
        KitapTurRes kitapTurRes = kitapTurService.findById(id);
        return new ResponseEntity<>(kitapTurRes, HttpStatus.OK);
    }
}
