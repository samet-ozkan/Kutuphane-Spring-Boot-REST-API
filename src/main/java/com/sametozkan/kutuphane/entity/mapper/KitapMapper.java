package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KitapReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.entity.model.Kitap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KitapMapper {

    public Kitap convertToEntity(KitapReq kitapReq) {
        Kitap kitap = Kitap.builder()
                .adi(kitapReq.getAdi())
                .isbn(kitapReq.getIsbn())
                .aciklama(kitapReq.getAciklama())
                .yayinYili(kitapReq.getYayinYili())
                .otomatikOlusturuldu(kitapReq.getOtomatikOlusturuldu())
                .build();

        if (kitapReq.getOtomatikOlusturuldu()) {
            kitap.setChatgptPuani(kitapReq.getChatgptPuani());
            kitap.setChatgptYorumu(kitapReq.getChatgptYorumu());
        }

        return kitap;
    }

    public KitapRes convertToResponse(Kitap kitap) {
        return KitapRes.builder()
                .id(kitap.getId())
                .isbn(kitap.getIsbn())
                .adi(kitap.getAdi())
                .yayinYili(kitap.getYayinYili())
                .aciklama(kitap.getAciklama())
                .build();
    }

    public List<KitapRes> convertToResponse(List<Kitap> kitaplar) {
        return kitaplar.stream().map(this::convertToResponse).toList();
    }
}
