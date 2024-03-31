package com.sametozkan.kutuphane.entity.dto.response;

import com.sametozkan.kutuphane.entity.model.Kitap;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class YazarRes {
    private Long id;
    private String adi;
    private String soyadi;
    private List<KitapRes> kitaplar;
}
