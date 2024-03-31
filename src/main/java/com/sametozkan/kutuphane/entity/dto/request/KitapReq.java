package com.sametozkan.kutuphane.entity.dto.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class KitapReq {

    private Long isbn;
    private String adi;
    private Integer yayinYili;
    private String aciklama;
    @Nullable
    private Double chatgptPuani;
    @Nullable
    private String chatgptYorumu;
    private Boolean otomatikOlusturuldu;
}
