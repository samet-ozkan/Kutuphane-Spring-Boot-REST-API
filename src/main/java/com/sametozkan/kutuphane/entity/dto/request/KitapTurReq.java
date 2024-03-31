package com.sametozkan.kutuphane.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KitapTurReq {
    private Long kitap_id;
    private Long tur_id;
}
