package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KitapKutuphaneRes {

    private Long id;
    private KitapRes kitap;
    private KutuphaneRes kutuphane;
}
