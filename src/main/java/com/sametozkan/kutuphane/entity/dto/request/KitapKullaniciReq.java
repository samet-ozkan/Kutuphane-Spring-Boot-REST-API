package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class KitapKullaniciReq {

    private Long kitapId;
    private Long kullaniciAccountId;
    private Long kutuphaneId;
    private Boolean iadeDurumu;
}
