package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KitapYorumReq;
import com.sametozkan.kutuphane.entity.dto.request.KutuphaneYorumReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapYorumRes;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneYorumRes;
import com.sametozkan.kutuphane.entity.model.KitapYorum;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.model.KutuphaneYorum;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KitapYorumMapper {

    private final KullaniciRepository kullaniciRepository;
    private final KitapRepository kitapRepository;
    private final KullaniciMapper kullaniciMapper;
    private final KitapMapper kitapMapper;

    public KitapYorum convertToEntity(KitapYorumReq kitapYorumReq) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(kitapYorumReq.getKullanici_id()).orElseThrow(EntityNotFoundException::new);
        return KitapYorum.builder()
                .yorum(kitapYorumReq.getYorum())
                .kullanici(kullanici)
                .spoiler(kitapYorumReq.getSpoiler())
                .kitap(kitapRepository.getReferenceById(kitapYorumReq.getKitap_id()))
                .build();
    }

    public KitapYorumRes convertToResponse(KitapYorum kitapYorum) {
        return KitapYorumRes.builder()
                .id(kitapYorum.getId())
                .yorum(kitapYorum.getYorum())
                .spoiler(kitapYorum.getSpoiler())
                .kullanici(kullaniciMapper.convertToResponse(kitapYorum.getKullanici()))
                .kitap(kitapMapper.convertToResponse(kitapYorum.getKitap()))
                .build();
    }

    public List<KitapYorumRes> convertToResponse(List<KitapYorum> kitapYorumList) {
        return kitapYorumList.stream().map(this::convertToResponse).toList();
    }
}

