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

    public Yazar convertToEntity(YazarReq yazarReq) {
        return Yazar.builder()
                .adi(yazarReq.getAdi())
                .soyadi(yazarReq.getSoyadi())
                .build();
    }

    public List<Yazar> convertToEntity(List<YazarReq> yazarReqList) {
        return yazarReqList.stream().map(this::convertToEntity).toList();
    }

    public YazarRes convertToResponse(Yazar yazar) {
        return YazarRes.builder()
                .id(yazar.getId())
                .adi(yazar.getAdi())
                .soyadi(yazar.getSoyadi())
                .build();
    }

    public List<YazarRes> convertToResponse(List<Yazar> yazarlar) {
        return yazarlar.stream().map(this::convertToResponse).toList();
    }
}
