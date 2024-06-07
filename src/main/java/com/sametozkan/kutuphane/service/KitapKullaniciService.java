package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.mapper.KitapKullaniciMapper;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.repository.KitapKullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class KitapKullaniciService {

    private final KitapKullaniciRepository kitapKullaniciRepository;
    private final KitapRepository kitapRepository;
    private final KullaniciRepository kullaniciRepository;
    private final KutuphaneRepository kutuphaneRepository;
    private final KitapKullaniciMapper kitapKullaniciMapper;

    @Transactional
    public void save(KitapKullaniciReq kitapKullaniciReq) {
        kitapKullaniciRepository.save(kitapKullaniciMapper.convertToEntity(kitapKullaniciReq));
    }

    @Transactional
    public KitapKullaniciRes update(Long id, KitapKullaniciReq kitapKullaniciReq) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kitapKullanici.setKitap(kitapRepository.getReferenceById(kitapKullaniciReq.getKitapId()));
        kitapKullanici.setKullanici(kullaniciRepository.getReferenceById(kitapKullaniciReq.getKullaniciId()));
        kitapKullanici.setKutuphane(kutuphaneRepository.getReferenceById(kitapKullaniciReq.getKutuphaneId()));
        kitapKullanici.setIadeDurumu(kitapKullaniciReq.getIadeDurumu());

        kitapKullaniciRepository.save(kitapKullanici);
        return kitapKullaniciMapper.convertToResponse(kitapKullanici);
    }

    public List<KitapKullaniciRes> findAll() {
        return kitapKullaniciRepository.findAll().stream().map(kitapKullaniciMapper::convertToResponse).toList();
    }

    public KitapKullaniciRes findById(Long id) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullanici);
    }

    public List<KitapKullaniciRes> findByKullaniciIdAndIadeDurumuIsNull(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountID(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndIadeDurumuIsNull(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapKullaniciRes> findByKullaniciIdAndIadeDurumuIsFalse(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountID(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndIadeDurumuIsFalse(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapKullaniciRes> findByKullaniciIdAndIadeDurumuIsTrue(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountID(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndIadeDurumuIsTrue(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public void deleteById(Long id){
        kitapKullaniciRepository.deleteById(id);
    }
}
