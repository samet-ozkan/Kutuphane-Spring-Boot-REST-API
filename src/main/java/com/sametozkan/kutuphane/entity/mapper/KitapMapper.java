package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KitapReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.entity.model.Kitap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KitapMapper {

    public Kitap convertToEntity(KitapReq kitapReq) {
        Kitap kitap = Kitap.builder()
                .adi(kitapReq.getAdi())
                .isbn(kitapReq.getIsbn())
                .aciklama(kitapReq.getAciklama())
                .yayinTarihi(kitapReq.getYayinTarihi())
                .dil(kitapReq.getDil())
                .sayfaSayisi(kitapReq.getSayfaSayisi())
                .build();

        return kitap;
    }

    public KitapRes convertToResponse(Kitap kitap) {
        return KitapRes.builder()
                .id(kitap.getId())
                .isbn(kitap.getIsbn())
                .adi(kitap.getAdi())
                .yayinTarihi(kitap.getYayinTarihi())
                .dil(kitap.getDil())
                .sayfaSayisi(kitap.getSayfaSayisi())
                .sayfaSayisi(kitap.getSayfaSayisi())
                .aciklama(kitap.getAciklama())
                .build();
    }

    public List<KitapRes> convertToResponse(List<Kitap> kitaplar) {
        return kitaplar.stream().map(this::convertToResponse).toList();
    }
}
