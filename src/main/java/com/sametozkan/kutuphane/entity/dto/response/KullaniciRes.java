package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class KullaniciRes {

    private Long id;
    private String adi;
    private String soyadi;
    private String email;
    private AccountRes account;
    private List<KitapRes> kitaplar;
}
