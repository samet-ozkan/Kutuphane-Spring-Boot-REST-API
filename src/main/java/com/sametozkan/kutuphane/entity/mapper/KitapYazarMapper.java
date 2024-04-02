package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KitapYazarReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapYazarRes;
import com.sametozkan.kutuphane.entity.model.KitapYazar;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.YazarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KitapYazarMapper {

    private final KitapRepository kitapRepository;
    private final YazarRepository yazarRepository;
    private final KitapMapper kitapMapper;
    private final YazarMapper yazarMapper;

    public KitapYazar convertToEntity(KitapYazarReq kitapYazarReq) {
        return KitapYazar.builder()
                .kitap(kitapRepository.getReferenceById(kitapYazarReq.getKitap_id()))
                .yazar(yazarRepository.getReferenceById(kitapYazarReq.getYazar_id()))
                .build();
    }

    public KitapYazarRes convertToResponse(KitapYazar kitapYazar) {
        return KitapYazarRes.builder()
                .id(kitapYazar.getId())
                .kitap(kitapMapper.convertToResponse(kitapYazar.getKitap()))
                .yazar(yazarMapper.convertToResponse(kitapYazar.getYazar()))
                .build();
    }

    public List<KitapYazarRes> convertToResponse(List<KitapYazar> kitapYazarList) {
        return kitapYazarList.stream().map(this::convertToResponse).toList();
    }
}
