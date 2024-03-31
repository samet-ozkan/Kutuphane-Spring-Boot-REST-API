package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class KitapKutuphaneReq {

    private Long kitap_id;
    private Long kutuphane_id;
    private Integer stok;
}
