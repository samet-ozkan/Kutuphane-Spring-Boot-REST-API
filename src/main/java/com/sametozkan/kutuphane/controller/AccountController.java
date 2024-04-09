package com.sametozkan.kutuphane.controller;

import com.sametozkan.kutuphane.entity.dto.request.AccountReq;
import com.sametozkan.kutuphane.entity.dto.response.AccountRes;
import com.sametozkan.kutuphane.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void save(@RequestBody AccountReq accountReq) {
        accountService.save(accountReq);
    }

    @GetMapping("/{id}")
    public AccountRes findById(@PathVariable Long id) {
        return accountService.findById(id);
    }
}
