package com.sametozkan.kutuphane.entity.dto.request;

import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KitapReq {

    private Long isbn;
    private String adi;
    private String yayinTarihi;
    private String dil;
    private Integer sayfaSayisi;
    private String aciklama;
    private List<YazarReq> yazarlar;
    private List<TurReq> turler;
}
