package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class KullaniciRegisterReq {

    private AccountReq account;
    private KullaniciReq kullanici;
}
