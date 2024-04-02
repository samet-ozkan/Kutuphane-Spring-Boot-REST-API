package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KullaniciRes;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KullaniciMapper {

    private final KitapMapper kitapMapper;

    public Kullanici convertToEntity(KullaniciReq kullaniciReq) {

        return Kullanici.builder()
                .adi(kullaniciReq.getAdi())
                .soyadi(kullaniciReq.getSoyadi())
                .email(kullaniciReq.getEmail())
                .password(kullaniciReq.getPassword())
                .build();
    }

    public KullaniciRes convertToResponse(Kullanici kullanici) {
        return KullaniciRes.builder()
                .id(kullanici.getId())
                .email(kullanici.getEmail())
                .adi(kullanici.getAdi())
                .soyadi(kullanici.getSoyadi())
                .kitaplar(kitapMapper.convertToResponse(kullanici.getKitaplar().stream().map(KitapKullanici::getKitap).toList()))
                .build();
    }

    public List<KullaniciRes> convertToResponse(List<Kullanici> kullanicilar) {
        return kullanicilar.stream().map(this::convertToResponse).toList();
    }
}
