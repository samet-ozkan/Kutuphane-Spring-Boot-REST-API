package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.config.googlebooks.BooksClient;
import com.sametozkan.kutuphane.entity.dto.request.AccountReq;
import com.sametozkan.kutuphane.entity.dto.response.AccountRes;
import com.sametozkan.kutuphane.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AccountReq accountReq) {
        accountService.save(accountReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountRes> findById(@PathVariable Long id) {
        AccountRes accountRes = accountService.findById(id);
        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }
}
