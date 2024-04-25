package com.sametozkan.kutuphane.entity.dto.request;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KitapReq {

    private Long isbn;
    private String adi;
    private String yayinTarihi;
    private String dil;
    private Integer sayfaSayisi;
    private String aciklama;
}
