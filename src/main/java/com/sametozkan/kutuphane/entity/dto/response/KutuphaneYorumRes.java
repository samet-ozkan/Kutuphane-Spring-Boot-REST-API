package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KutuphaneYorumRes {

    private Long id;
    private String yorum;
    private KullaniciRes kullanici;
    private KutuphaneRes kutuphane;
}
