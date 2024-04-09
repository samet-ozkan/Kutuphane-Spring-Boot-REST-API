package com.sametozkan.kutuphane.entity.dto.request;

import com.sametozkan.kutuphane.util.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
public class AccountReq {

    private String email;
    private String password;
    private String type;
}
