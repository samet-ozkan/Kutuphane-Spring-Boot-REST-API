package com.sametozkan.kutuphane.entity.dto.response;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KitapRes {

    private Long id;
    private Long isbn;
    private String adi;
    private String yayinTarihi;
    private String dil;
    private Integer sayfaSayisi;
    private String chatGptYorumu;
    private String aciklama;
    private List<YazarRes> yazarlar;
    private List<TurRes> turler;
    private List<KutuphaneRes> kutuphaneler;
    private List<KullaniciRes> kullanicilar;
}
