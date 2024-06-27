package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class KullaniciReq {

    private String adi;
    private String soyadi;
    private String telefonNumarasi;
    private String adres;
    private String dogumTarihi;
}
