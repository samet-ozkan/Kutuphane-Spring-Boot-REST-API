package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.TurReq;
import com.sametozkan.kutuphane.entity.dto.response.TurRes;
import com.sametozkan.kutuphane.service.TurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tur")
@RequiredArgsConstructor
public class TurController {

    private final TurService turService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TurReq turReq) {
        turService.save(turReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PutMapping("/{id}")
    public ResponseEntity<TurRes> update(@PathVariable Long id, @RequestBody TurReq turReq) {
        TurRes turRes = turService.update(id, turReq);
        return new ResponseEntity<>(turRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TurRes>> findAll() {
        List<TurRes> turResList = turService.findAll();
        return new ResponseEntity<>(turResList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurRes> findById(@PathVariable Long id) {
        TurRes turRes = turService.findById(id);
        return new ResponseEntity<>(turRes, HttpStatus.OK);
    }
}
