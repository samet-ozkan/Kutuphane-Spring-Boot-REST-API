package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.TurReq;
import com.sametozkan.kutuphane.entity.dto.response.TurRes;
import com.sametozkan.kutuphane.entity.model.KitapTur;
import com.sametozkan.kutuphane.entity.model.Tur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TurMapper {

    private final KitapMapper kitapMapper;

    public Tur convertToEntity(TurReq turReq) {
        return Tur.builder()
                .tur(turReq.getTur())
                .build();
    }

    public TurRes convertToResponse(Tur tur) {
        return TurRes.builder()
                .id(tur.getId())
                .tur(tur.getTur())
                .kitaplar(kitapMapper.convertToResponse(tur.getKitaplar().stream().map(KitapTur::getKitap).toList()))
                .build();
    }

    public List<TurRes> convertToResponse(List<Tur> turler) {
        return turler.stream().map(this::convertToResponse).toList();
    }
}
