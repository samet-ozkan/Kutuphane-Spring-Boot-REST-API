package com.sametozkan.kutuphane.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sametozkan.kutuphane.config.jwt.UserDetailsImpl;
import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.dto.response.KitapOnerisiRes;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneRes;
import com.sametozkan.kutuphane.entity.model.Kutuphane;
import com.sametozkan.kutuphane.service.KitapKullaniciService;
import com.sametozkan.kutuphane.service.KutuphaneService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/kitap-kullanici")
@RequiredArgsConstructor
public class KitapKullaniciController {

    private final KitapKullaniciService kitapKullaniciService;
    private final KutuphaneService kutuphaneService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_KULLANICI') and authentication.principal.id == #kitapKullaniciReq.kullaniciAccountId)")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody KitapKullaniciReq kitapKullaniciReq) {
        kitapKullaniciService.save(kitapKullaniciReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_KULLANICI') and authentication.principal.id == #kitapKullaniciReq.kullaniciAccountId)")
    @PutMapping("/{id}")
    public ResponseEntity<KitapKullaniciRes> update(@PathVariable Long id, @RequestBody KitapKullaniciReq kitapKullaniciReq) {
        KitapKullaniciRes kitapKullaniciRes = kitapKullaniciService.update(id, kitapKullaniciReq);
        return new ResponseEntity<>(kitapKullaniciRes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<KitapKullaniciRes> findAll() {
        return kitapKullaniciService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public KitapKullaniciRes findById(@PathVariable Long id) {
        return kitapKullaniciService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_KULLANICI') and authentication.principal.id == #accountId)")
    @GetMapping("/onay-bekleyenler/{accountId}")
    public ResponseEntity<List<KitapKullaniciRes>> findByKullaniciIdAndOnaylandiIsNull(@PathVariable Long accountId) {
        List<KitapKullaniciRes> kitapKullaniciResList = kitapKullaniciService.findByKullaniciIdAndOnaylandiIsNull(accountId);
        return new ResponseEntity<>(kitapKullaniciResList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_KULLANICI') and authentication.principal.id == #accountId)")
    @GetMapping("/onaylandi/{accountId}")
    public ResponseEntity<List<KitapKullaniciRes>> findByKullaniciIdAndOnaylandiIsFalse(@PathVariable Long accountId) {
        List<KitapKullaniciRes> kitapKullaniciResList = kitapKullaniciService.findByKullaniciIdAndOnaylandiIsFalse(accountId);
        return new ResponseEntity<>(kitapKullaniciResList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_KULLANICI') and authentication.principal.id == #accountId)")
    @GetMapping("/reddedildi/{accountId}")
    public ResponseEntity<List<KitapKullaniciRes>> findByKullaniciIdAndOnaylandiIsTrue(@PathVariable Long accountId) {
        List<KitapKullaniciRes> kitapKullaniciResList = kitapKullaniciService.findByKullaniciIdAndOnaylandiIsTrue(accountId);
        return new ResponseEntity<>(kitapKullaniciResList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_KUTUPHANE') and authentication.principal.id == #accountId)")
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

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PostMapping("/onayla/{kitapKullaniciId}")
    public ResponseEntity<Void> kitapIstegiOnayla(@PathVariable("kitapKullaniciId") Long kitapKullaniciId) {
        kitapKullaniciService.kitapIstegiOnayla(kitapKullaniciId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PostMapping("/reddet/{kitapKullaniciId}")
    public ResponseEntity<Void> kitapIstegiReddet(@PathVariable("kitapKullaniciId") Long kitapKullaniciId) {
        kitapKullaniciService.kitapIstegiReddet(kitapKullaniciId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PostMapping("/teslim-edildi/{kitapKullaniciId}")
    public ResponseEntity<Void> teslimEdildi(@PathVariable Long kitapKullaniciId) {
        kitapKullaniciService.teslimEdildi(kitapKullaniciId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_KUTUPHANE') and authentication.principal.id == #kutuphaneAccountId)")
    @GetMapping("/teslim-edilmeyenler/{kutuphaneAccountId}")
    public ResponseEntity<List<KitapKullaniciRes>> teslimEdilmeyenler(@PathVariable Long kutuphaneAccountId) {
        List<KitapKullaniciRes> kitapKullaniciResList = kitapKullaniciService.teslimEdilmeyenler(kutuphaneAccountId);
        return new ResponseEntity<>(kitapKullaniciResList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KULLANICI')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        kitapKullaniciService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
