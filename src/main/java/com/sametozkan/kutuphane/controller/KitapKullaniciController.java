package com.sametozkan.kutuphane.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.dto.response.KitapOnerisiRes;
import com.sametozkan.kutuphane.service.KitapKullaniciService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap-kullanici")
@RequiredArgsConstructor
public class KitapKullaniciController {

    private final KitapKullaniciService kitapKullaniciService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody KitapKullaniciReq kitapKullaniciReq) {
        kitapKullaniciService.save(kitapKullaniciReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitapKullaniciRes> update(@PathVariable Long id, @RequestBody KitapKullaniciReq kitapKullaniciReq) {
        KitapKullaniciRes kitapKullaniciRes = kitapKullaniciService.update(id, kitapKullaniciReq);
        return new ResponseEntity<>(kitapKullaniciRes, HttpStatus.OK);
    }

    @GetMapping
    public List<KitapKullaniciRes> findAll() {
        return kitapKullaniciService.findAll();
    }

    @GetMapping("/{id}")
    public KitapKullaniciRes findById(@PathVariable Long id) {
        return kitapKullaniciService.findById(id);
    }

    @GetMapping("/onay-bekleyenler/{accountId}")
    public ResponseEntity<List<KitapKullaniciRes>> findByKullaniciIdAndOnaylandiIsNull(@PathVariable Long accountId) {
        List<KitapKullaniciRes> kitapKullaniciResList = kitapKullaniciService.findByKullaniciIdAndOnaylandiIsNull(accountId);
        return new ResponseEntity<>(kitapKullaniciResList, HttpStatus.OK);
    }

    @GetMapping("/onaylandi/{accountId}")
    public ResponseEntity<List<KitapKullaniciRes>> findByKullaniciIdAndOnaylandiIsFalse(@PathVariable Long accountId) {
        List<KitapKullaniciRes> kitapKullaniciResList = kitapKullaniciService.findByKullaniciIdAndOnaylandiIsFalse(accountId);
        return new ResponseEntity<>(kitapKullaniciResList, HttpStatus.OK);
    }

    @GetMapping("/reddedildi/{accountId}")
    public ResponseEntity<List<KitapKullaniciRes>> findByKullaniciIdAndOnaylandiIsTrue(@PathVariable Long accountId) {
        List<KitapKullaniciRes> kitapKullaniciResList = kitapKullaniciService.findByKullaniciIdAndOnaylandiIsTrue(accountId);
        return new ResponseEntity<>(kitapKullaniciResList, HttpStatus.OK);
    }

    @GetMapping("/kutuphane/{accountId}")
    public ResponseEntity<List<KitapKullaniciRes>> findByKutuphaneId(@PathVariable Long accountId) {
        List<KitapKullaniciRes> kitapKullaniciResList = kitapKullaniciService.findByKutuphaneId(accountId);
        return new ResponseEntity<>(kitapKullaniciResList, HttpStatus.OK);
    }

    @GetMapping("/kitap-onerileri/{accountId}")
    public ResponseEntity<List<KitapOnerisiRes>> fetchKitapOnerileri(@PathVariable Long accountId) throws JsonProcessingException {
        List<KitapOnerisiRes> kitapOnerisiResList = kitapKullaniciService.fetchKitapOnerileri(accountId);
        return new ResponseEntity<>(kitapOnerisiResList, HttpStatus.OK);
    }

    @PostMapping("/onayla/{kitapKullaniciId}")
    public ResponseEntity<Void> kitapIstegiOnayla(@PathVariable Long kitapKullaniciId) {
        kitapKullaniciService.kitapIstegiOnayla(kitapKullaniciId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reddet/{kitapKullaniciId}")
    public ResponseEntity<Void> kitapIstegiReddet(@PathVariable Long kitapKullaniciId) {
        kitapKullaniciService.kitapIstegiReddet(kitapKullaniciId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        kitapKullaniciService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
