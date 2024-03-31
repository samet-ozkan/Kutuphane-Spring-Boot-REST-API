package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KitapTurReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.entity.dto.response.KitapTurRes;
import com.sametozkan.kutuphane.entity.model.KitapTur;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.TurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KitapTurMapper {

    private TurRepository turRepository;
    private KitapRepository kitapRepository;
    private TurMapper turMapper;
    private KitapMapper kitapMapper;

    public KitapTur convertToEntity(KitapTurReq kitapTurReq) {
        return KitapTur.builder()
                .tur(turRepository.getReferenceById(kitapTurReq.getTur_id()))
                .kitap(kitapRepository.getReferenceById(kitapTurReq.getKitap_id()))
                .build();
    }

    public KitapTurRes convertToResponse(KitapTur kitapTur) {
        return KitapTurRes.builder()
                .id(kitapTur.getId())
                .turRes(turMapper.convertToResponse(kitapTur.getTur()))
                .kitapRes(kitapMapper.convertToResponse(kitapTur.getKitap()))
                .build();
    }

    public List<KitapTurRes> convertToResponse(List<KitapTur> kitapTurler) {
        return kitapTurler.stream().map(this::convertToResponse).toList();
    }
}
