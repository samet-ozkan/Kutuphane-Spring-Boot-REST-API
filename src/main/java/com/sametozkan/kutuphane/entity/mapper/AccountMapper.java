package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.AccountReq;
import com.sametozkan.kutuphane.entity.dto.response.AccountRes;
import com.sametozkan.kutuphane.entity.model.Account;
import com.sametozkan.kutuphane.entity.repository.AccountRepository;
import com.sametozkan.kutuphane.util.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class AccountMapper {

    private final AccountRepository accountRepository;
    @Lazy private final PasswordEncoder passwordEncoder;

    public Account convertToEntity(AccountReq accountReq) {
        return Account.builder()
                .email(accountReq.getEmail())
                .password(passwordEncoder.encode(accountReq.getPassword()))
                .type(accountReq.getType())
                .build();
    }

    public AccountRes convertToResponse(Account account) {
        return AccountRes.builder()
                .id(account.getId())
                .email(account.getEmail())
                .type(account.getType())
                .build();
    }
}
