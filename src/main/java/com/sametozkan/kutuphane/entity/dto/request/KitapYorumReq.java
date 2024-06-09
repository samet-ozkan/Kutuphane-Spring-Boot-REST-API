package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class KitapYorumReq {

    private String yorum;
    private Boolean spoiler;
    private Long kullanici_id;
    private Long kitap_id;
}
