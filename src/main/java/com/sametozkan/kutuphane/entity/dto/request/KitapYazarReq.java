package com.sametozkan.kutuphane.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KitapYazarReq {

    private Long kitap_id;
    private Long yazar_id;
}
