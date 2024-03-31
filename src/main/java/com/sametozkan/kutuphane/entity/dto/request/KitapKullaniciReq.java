package com.sametozkan.kutuphane.entity.dto.request;

import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.entity.dto.response.KullaniciRes;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KitapKullaniciReq {

    private Long kitapId;
    private Long kullaniciId;
    private Long kutuphaneId;
    private Boolean iadeDurumu;
}
