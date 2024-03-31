package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.KullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KullaniciRes;
import com.sametozkan.kutuphane.entity.mapper.KullaniciMapper;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final KullaniciMapper kullaniciMapper;

    @Transactional
    public void save(KullaniciReq kullaniciReq) {
        kullaniciRepository.save(kullaniciMapper.convertToEntity(kullaniciReq));
    }

    @Transactional
    public void update(Long id, KullaniciReq kullaniciReq) {
        Kullanici kullanici = kullaniciRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kullanici.setAdi(kullaniciReq.getAdi());
        kullanici.setSoyadi(kullaniciReq.getSoyadi());
        kullanici.setEmail(kullaniciReq.getEmail());
        kullanici.setPassword(kullaniciReq.getPassword());

        kullaniciRepository.save(kullanici);
    }

    public List<KullaniciRes> findAll() {
        return kullaniciMapper.convertToResponse(kullaniciRepository.findAll());
    }

    public KullaniciRes findById(Long id) {
        Kullanici kullanici = kullaniciRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kullaniciMapper.convertToResponse(kullanici);
    }
}
