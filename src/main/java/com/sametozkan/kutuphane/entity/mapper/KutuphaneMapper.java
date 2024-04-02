package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneRes;
import com.sametozkan.kutuphane.entity.model.KitapKutuphane;
import com.sametozkan.kutuphane.entity.model.Kutuphane;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KutuphaneMapper {

    private final KitapMapper kitapMapper;

    public Kutuphane convertToEntity(KutuphaneReq kutuphaneReq) {
        return Kutuphane.builder()
                .adi(kutuphaneReq.getAdi())
                .email(kutuphaneReq.getEmail())
                .adresi(kutuphaneReq.getAdresi())
                .password(kutuphaneReq.getPassword())
                .teslimSuresi(kutuphaneReq.getTeslimSuresi())
                .build();
    }

    public KutuphaneRes convertToResponse(Kutuphane kutuphane) {
        return KutuphaneRes.builder()
                .id(kutuphane.getId())
                .adi(kutuphane.getAdi())
                .email(kutuphane.getEmail())
                .adresi(kutuphane.getAdresi())
                .teslimSuresi(kutuphane.getTeslimSuresi())
                .kitaplar(kitapMapper.convertToResponse(kutuphane.getKitaplar().stream().map(KitapKutuphane::getKitap).toList()))
                .build();
    }

    public List<KutuphaneRes> convertToResponse(List<Kutuphane> kutuphaneler) {
        return kutuphaneler.stream().map(this::convertToResponse).toList();
    }
}
