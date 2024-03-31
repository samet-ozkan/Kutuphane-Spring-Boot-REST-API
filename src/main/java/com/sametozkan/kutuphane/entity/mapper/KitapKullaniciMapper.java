package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.entity.repository.KitapKullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import com.sametozkan.kutuphane.util.Utils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KitapKullaniciMapper {

    private KitapKullaniciRepository kitapKullaniciRepository;
    private KitapRepository kitapRepository;
    private KullaniciRepository kullaniciRepository;
    private KutuphaneRepository kutuphaneRepository;
    private KitapMapper kitapMapper;
    private KullaniciMapper kullaniciMapper;
    private KutuphaneMapper kutuphaneMapper;

    public KitapKullanici convertToEntity(KitapKullaniciReq kitapKullaniciReq) {
        return KitapKullanici.builder()
                .kitap(kitapRepository.getReferenceById(kitapKullaniciReq.getKitapId()))
                .kullanici(kullaniciRepository.getReferenceById(kitapKullaniciReq.getKullaniciId()))
                .iadeDurumu(kitapKullaniciReq.getIadeDurumu())
                .build();
    }

    public KitapKullaniciRes convertToResponse(KitapKullanici kitapKullanici) {
        return KitapKullaniciRes.builder()
                .id(kitapKullanici.getId())
                .kitap(kitapMapper.convertToResponse(kitapKullanici.getKitap()))
                .kullanici(kullaniciMapper.convertToResponse(kitapKullanici.getKullanici()))
                .iadeDurumu(kitapKullanici.getIadeDurumu())
                .alimTarihi(kitapKullanici.getAlimTarihi())
                .teslimTarihi(kitapKullanici.getTeslimTarihi())
                .kutuphane(kutuphaneMapper.convertToResponse(kitapKullanici.getKutuphane()))
                .build();
    }
}
