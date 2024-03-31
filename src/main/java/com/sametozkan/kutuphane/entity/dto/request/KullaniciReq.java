package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class KullaniciReq {

    private String adi;
    private String soyadi;
    private String email;
    private String password;
}
