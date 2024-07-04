package com.sametozkan.kutuphane.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sametozkan.kutuphane.config.googlebooks.BooksClient;
import com.sametozkan.kutuphane.entity.dto.request.KitapReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.service.KitapService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitap")
@RequiredArgsConstructor
public class KitapController {

    private final KitapService kitapService;
    private final BooksClient booksClient;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @PostMapping
    public ResponseEntity<Long> save(@RequestBody KitapReq kitapReq) {
        Long kitapId = kitapService.save(kitapReq);
        return new ResponseEntity<>(kitapId, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
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

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<KitapRes> findByIsbn(@PathVariable Long isbn) {
        KitapRes kitapRes = kitapService.findByIsbn(isbn);
        if (kitapRes == null)
            throw new EntityNotFoundException("Kitap not found by isbn in database!");
        else
            return new ResponseEntity<>(kitapRes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_KUTUPHANE')")
    @GetMapping("/fetch/{isbn}")
    public ResponseEntity<KitapReq> fetchByIsbn(@PathVariable Long isbn) {
        KitapReq kitapReq = kitapService.fetchByIsbn(isbn);
        if (kitapReq != null) {
            return new ResponseEntity<>(kitapReq, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
