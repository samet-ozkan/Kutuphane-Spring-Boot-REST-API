package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class KutuphaneReq {
    private String adi;
    private String adresi;
    private Integer teslimSuresi;
}
