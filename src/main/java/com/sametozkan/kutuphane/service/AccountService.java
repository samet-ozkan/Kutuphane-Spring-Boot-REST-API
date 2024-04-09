package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.AccountReq;
import com.sametozkan.kutuphane.entity.dto.response.AccountRes;
import com.sametozkan.kutuphane.entity.mapper.AccountMapper;
import com.sametozkan.kutuphane.entity.model.Account;
import com.sametozkan.kutuphane.entity.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public Account save(AccountReq accountReq) {
        return accountRepository.save(accountMapper.convertToEntity(accountReq));
    }

    public AccountRes findById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return accountMapper.convertToResponse(account);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
}
