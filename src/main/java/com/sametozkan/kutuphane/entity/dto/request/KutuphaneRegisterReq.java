package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class KutuphaneRegisterReq {

    private AccountReq account;
    private KutuphaneReq kutuphane;
    private String dogrulamaKodu;
}
