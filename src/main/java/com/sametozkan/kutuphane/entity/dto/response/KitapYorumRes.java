package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KitapYorumRes {

    private Long id;
    private String yorum;
    private Boolean spoiler;
    private KullaniciRes kullanici;
    private KitapRes kitap;
}
