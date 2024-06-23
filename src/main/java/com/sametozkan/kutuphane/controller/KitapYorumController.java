package com.sametozkan.kutuphane.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sametozkan.kutuphane.entity.dto.request.KitapYorumReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapYorumRes;
import com.sametozkan.kutuphane.service.KitapYorumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-yorum")
@RequiredArgsConstructor
public class KitapYorumController {

    private final KitapYorumService kitapYorumService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KULLANICI')")
    @PostMapping
    public ResponseEntity<Long> save(@RequestBody KitapYorumReq kitapYorumReq) throws JsonProcessingException {
        Long id = kitapYorumService.save(kitapYorumReq).getId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{kitapId}")
    public ResponseEntity<List<KitapYorumRes>> findByKitapId(@PathVariable Long kitapId) {
        List<KitapYorumRes> yorumlar = kitapYorumService.findByKitapId(kitapId);
        return new ResponseEntity<>(yorumlar, HttpStatus.OK);
    }
}

