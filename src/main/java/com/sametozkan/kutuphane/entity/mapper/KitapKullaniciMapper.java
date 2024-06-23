package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.repository.KitapKullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KitapKullaniciMapper {

    private final KitapKullaniciRepository kitapKullaniciRepository;
    private final KitapRepository kitapRepository;
    private final KullaniciRepository kullaniciRepository;
    private final KutuphaneRepository kutuphaneRepository;
    private final KitapMapper kitapMapper;
    private final KullaniciMapper kullaniciMapper;
    private final KutuphaneMapper kutuphaneMapper;

    public KitapKullanici convertToEntity(KitapKullaniciReq kitapKullaniciReq) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(kitapKullaniciReq.getKullaniciAccountId()).orElseThrow(EntityNotFoundException::new);
        return KitapKullanici.builder()
                .kitap(kitapRepository.getReferenceById(kitapKullaniciReq.getKitapId()))
                .kutuphane(kutuphaneRepository.getReferenceById(kitapKullaniciReq.getKutuphaneId()))
                .kullanici(kullanici)
                .iadeDurumu(kitapKullaniciReq.getIadeDurumu())
                .build();
    }

    public KitapKullaniciRes convertToResponse(KitapKullanici kitapKullanici) {
        return KitapKullaniciRes.builder()
                .id(kitapKullanici.getId())
                .kitap(kitapMapper.convertToResponse(kitapKullanici.getKitap()))
                .kullanici(kullaniciMapper.convertToResponse(kitapKullanici.getKullanici()))
                .kutuphane(kutuphaneMapper.convertToResponse(kitapKullanici.getKutuphane()))
                .iadeDurumu(kitapKullanici.getIadeDurumu())
                .alimTarihi(kitapKullanici.getAlimTarihi())
                .teslimTarihi(kitapKullanici.getTeslimTarihi())
                .createdTime(kitapKullanici.getCreatedTime())
                .iadeDurumu(kitapKullanici.getIadeDurumu())
                .onaylandi(kitapKullanici.getOnaylandi())
                .build();
    }

    public List<KitapKullaniciRes> convertToResponse(List<KitapKullanici> kitapKullaniciList) {
        return kitapKullaniciList.stream().map(this::convertToResponse).toList();
    }
}
