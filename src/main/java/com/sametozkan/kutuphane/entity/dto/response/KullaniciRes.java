package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class KullaniciRes {

    private Long id;
    private String adi;
    private String soyadi;
    private String telefonNumarasi;
    private String adres;
    private String dogumTarihi;
    private String email;
    private AccountRes account;
    private List<KitapRes> kitaplar;
}
