package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KitapYazarRes {
    private Long id;
    private KitapRes kitap;
    private YazarRes yazar;
}
