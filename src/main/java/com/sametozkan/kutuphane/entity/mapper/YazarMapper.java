package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.YazarReq;
import com.sametozkan.kutuphane.entity.dto.response.YazarRes;
import com.sametozkan.kutuphane.entity.model.KitapYazar;
import com.sametozkan.kutuphane.entity.model.Yazar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class YazarMapper {

    private KitapMapper kitapMapper;

    public Yazar convertToEntity(YazarReq yazarReq) {
        return Yazar.builder()
                .adi(yazarReq.getAdi())
                .soyadi(yazarReq.getSoyadi())
                .build();
    }

    public YazarRes convertToResponse(Yazar yazar) {
        return YazarRes.builder()
                .id(yazar.getId())
                .adi(yazar.getAdi())
                .soyadi(yazar.getSoyadi())
                .kitaplar(kitapMapper.convertToResponse(yazar.getKitaplar().stream().map(KitapYazar::getKitap).toList()))
                .build();
    }

    public List<YazarRes> convertToResponse(List<Yazar> yazarlar) {
        return yazarlar.stream().map(this::convertToResponse).toList();
    }
}
