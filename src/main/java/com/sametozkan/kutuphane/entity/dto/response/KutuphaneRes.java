package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class KutuphaneRes {
    private Long id;
    private String adi;
    private String adresi;
    private String sehir;
    private AccountRes account;
    private List<KitapRes> kitaplar;
    private Integer teslimSuresi;
}
