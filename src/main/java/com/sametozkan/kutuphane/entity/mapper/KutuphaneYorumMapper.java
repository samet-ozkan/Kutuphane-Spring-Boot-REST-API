package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KutuphaneYorumReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneYorumRes;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.model.KutuphaneYorum;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KutuphaneYorumMapper {

    private final KullaniciRepository kullaniciRepository;
    private final KutuphaneRepository kutuphaneRepository;
    private final KullaniciMapper kullaniciMapper;
    private final KutuphaneMapper kutuphaneMapper;

    public KutuphaneYorum convertToEntity(KutuphaneYorumReq kutuphaneYorumReq) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(kutuphaneYorumReq.getKullanici_id()).orElseThrow(EntityNotFoundException::new);
        return KutuphaneYorum.builder()
                .yorum(kutuphaneYorumReq.getYorum())
                .kullanici(kullanici)
                .kutuphane(kutuphaneRepository.getReferenceById(kutuphaneYorumReq.getKutuphane_id()))
                .build();
    }

    public KutuphaneYorumRes convertToResponse(KutuphaneYorum kutuphaneYorum) {
        return KutuphaneYorumRes.builder()
                .id(kutuphaneYorum.getId())
                .yorum(kutuphaneYorum.getYorum())
                .kullanici(kullaniciMapper.convertToResponse(kutuphaneYorum.getKullanici()))
                .kutuphane(kutuphaneMapper.convertToResponse(kutuphaneYorum.getKutuphane()))
                .build();
    }

    public List<KutuphaneYorumRes> convertToResponse(List<KutuphaneYorum> kutuphaneYorumList) {
        return kutuphaneYorumList.stream().map(this::convertToResponse).toList();
    }
}
