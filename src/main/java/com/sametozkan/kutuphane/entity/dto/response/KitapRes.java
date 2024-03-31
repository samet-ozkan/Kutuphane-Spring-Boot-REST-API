package com.sametozkan.kutuphane.entity.dto.response;

import com.sametozkan.kutuphane.entity.model.Tur;
import com.sametozkan.kutuphane.entity.model.Yazar;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class KitapRes {

    private Long id;
    private Long isbn;
    private String adi;
    private Integer yayinYili;
    private String aciklama;
    @Nullable
    private Double chatgptPuani;
    @Nullable
    private String chatgptYorumu;
    private List<YazarRes> yazarlar;
    private List<TurRes> turler;
    private List<KutuphaneRes> kutuphaneler;
    private List<KullaniciRes> kullanicilar;
    private Boolean otomatikOlusturuldu;
}
