package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.KutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneRes;
import com.sametozkan.kutuphane.service.KutuphaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/kutuphane")
@RequiredArgsConstructor
public class KutuphaneController {

    private final KutuphaneService kutuphaneService;

    @PutMapping("/{id}")
    public ResponseEntity<KutuphaneRes> update(@PathVariable Long id, @RequestBody KutuphaneReq kutuphaneReq) {
        KutuphaneRes kutuphaneRes = kutuphaneService.update(id, kutuphaneReq);
        return new ResponseEntity<>(kutuphaneRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<KutuphaneRes>> findAll() {
        List<KutuphaneRes> kutuphaneResList = kutuphaneService.findAll();
        return new ResponseEntity<>(kutuphaneResList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KutuphaneRes> findById(@PathVariable Long id) {
        KutuphaneRes kutuphaneRes = kutuphaneService.findById(id);
        return new ResponseEntity<>(kutuphaneRes, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<KutuphaneRes> findByAccountId(@PathVariable Long accountId){
        KutuphaneRes kutuphaneRes = kutuphaneService.findByAccountId(accountId);
        return new ResponseEntity<>(kutuphaneRes, HttpStatus.OK);
    }
}
