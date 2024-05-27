package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KutuphaneYorumReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneYorumRes;
import com.sametozkan.kutuphane.entity.model.KutuphaneYorum;
import com.sametozkan.kutuphane.service.KitapKutuphaneService;
import com.sametozkan.kutuphane.service.KutuphaneYorumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/kutuphane-yorum")
@RequiredArgsConstructor
public class KutuphaneYorumController {

    private final KutuphaneYorumService kutuphaneYorumService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody KutuphaneYorumReq kutuphaneYorumReq) {
        Long id = kutuphaneYorumService.save(kutuphaneYorumReq).getId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{kutuphaneId}")
    public ResponseEntity<List<KutuphaneYorumRes>> findByKutuphaneId(@PathVariable Long kutuphaneId) {
        List<KutuphaneYorumRes> yorumlar = kutuphaneYorumService.findByKutuphaneId(kutuphaneId);
        return new ResponseEntity<>(yorumlar, HttpStatus.OK);
    }
}
