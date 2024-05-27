package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class KutuphaneYorumReq {

    private String yorum;
    private Long kullanici_id;
    private Long kutuphane_id;
}
