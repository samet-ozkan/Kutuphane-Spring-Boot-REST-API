package com.sametozkan.kutuphane.controller;

import com.google.gson.Gson;
import com.sametozkan.kutuphane.config.chatgpt.ChatRequest;
import com.sametozkan.kutuphane.config.chatgpt.ChatResponse;
import com.sametozkan.kutuphane.config.chatgpt.GptClient;
import com.sametozkan.kutuphane.config.chatgpt.GptResponse;
import com.sametozkan.kutuphane.config.googlebooks.Book;
import com.sametozkan.kutuphane.config.googlebooks.BookResponse;
import com.sametozkan.kutuphane.config.googlebooks.BooksClient;
import com.sametozkan.kutuphane.config.googlebooks.VolumeInfo;
import com.sametozkan.kutuphane.entity.dto.request.KitapReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.service.KitapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kitap")
@RequiredArgsConstructor
public class KitapController {

    private final KitapService kitapService;
    private final BooksClient booksClient;
    private final GptClient gptClient;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody KitapReq kitapReq) {
        kitapService.save(kitapReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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

    @GetMapping("/fetch/{isbn}")
    public ResponseEntity<KitapReq> fetchByIsbn(@PathVariable Long isbn) {
        KitapReq kitapReq = kitapService.fetchByIsbn(isbn);
        if (kitapReq != null) {
            return new ResponseEntity<>(kitapReq, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
