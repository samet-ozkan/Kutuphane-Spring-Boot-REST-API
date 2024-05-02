package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KitapKutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKutuphaneRes;
import com.sametozkan.kutuphane.entity.model.KitapKutuphane;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KitapKutuphaneMapper {

    private final KitapRepository kitapRepository;
    private final KutuphaneRepository kutuphaneRepository;
    private final KitapMapper kitapMapper;
    private final KutuphaneMapper kutuphaneMapper;

    public KitapKutuphane convertToEntity(KitapKutuphaneReq kitapKutuphaneReq) {
        return KitapKutuphane.builder()
                .kitap(kitapRepository.getReferenceById(kitapKutuphaneReq.getKitap_id()))
                .kutuphane(kutuphaneRepository.getReferenceById(kitapKutuphaneReq.getKutuphane_id()))
                .stok(kitapKutuphaneReq.getStok())
                .build();
    }

    public KitapKutuphaneRes convertToResponse(KitapKutuphane kitapKutuphane) {
        return KitapKutuphaneRes.builder()
                .id(kitapKutuphane.getId())
                .kutuphane(kutuphaneMapper.convertToResponse(kitapKutuphane.getKutuphane()))
                .kitap(kitapMapper.convertToResponse(kitapKutuphane.getKitap()))
                .stok(kitapKutuphane.getStok())
                .build();
    }

    public List<KitapKutuphaneRes> convertToResponse(List<KitapKutuphane> kitapKutuphaneler){
        return kitapKutuphaneler.stream().map(this::convertToResponse).toList();
    }
}
