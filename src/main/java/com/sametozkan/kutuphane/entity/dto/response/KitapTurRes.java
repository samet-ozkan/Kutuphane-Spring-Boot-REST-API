package com.sametozkan.kutuphane.entity.dto.response;

import com.sametozkan.kutuphane.entity.model.Kitap;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KitapTurRes {
    private Long id;
    private KitapRes kitapRes;
    private TurRes turRes;
}
