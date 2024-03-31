package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class KitapKullaniciRes {
    private Long id;
    private KitapRes kitap;
    private KutuphaneRes kutuphane;
    private KullaniciRes kullanici;
    private LocalDateTime alimTarihi;
    private LocalDateTime teslimTarihi;
    private Boolean iadeDurumu;
}
