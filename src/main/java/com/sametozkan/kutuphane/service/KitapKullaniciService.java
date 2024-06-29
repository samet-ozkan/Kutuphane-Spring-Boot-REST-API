package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.mapper.KitapKullaniciMapper;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.model.Kutuphane;
import com.sametozkan.kutuphane.entity.repository.KitapKullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import com.sametozkan.kutuphane.util.AccessPermission;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
        kitapKullanici.setKullanici(kullaniciRepository.getReferenceById(kitapKullaniciReq.getKullaniciAccountId()));
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

    public List<KitapKullaniciRes> findByKullaniciIdAndOnaylandiIsNull(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndOnaylandiIsNull(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapKullaniciRes> findByKullaniciIdAndOnaylandiIsFalse(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndOnaylandiIsFalse(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapKullaniciRes> findByKullaniciIdAndOnaylandiIsTrue(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndOnaylandiIsTrue(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapKullaniciRes> findByKutuphaneId(Long accountId) {
        Kutuphane kutuphane = kutuphaneRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKutuphaneId(kutuphane.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public void kitapIstegiOnayla(Long kitapKullaniciId) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(kitapKullaniciId).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kutuphane(kutuphaneRepository, kitapKullanici.getKutuphane().getId());
        kitapKullanici.setOnaylandi(true);
        kitapKullanici.setAlimTarihi(LocalDateTime.now());
        kitapKullaniciRepository.save(kitapKullanici);
    }

    public void kitapIstegiReddet(Long kitapKullaniciId) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(kitapKullaniciId).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kutuphane(kutuphaneRepository, kitapKullanici.getKutuphane().getId());
        kitapKullanici.setOnaylandi(false);
        kitapKullaniciRepository.save(kitapKullanici);
    }

    public void teslimEdildi(Long kitapKullaniciId){
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(kitapKullaniciId).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kutuphane(kutuphaneRepository, kitapKullanici.getKutuphane().getId());
        kitapKullanici.setIadeDurumu(true);
        kitapKullanici.setTeslimTarihi(LocalDateTime.now());
        kitapKullaniciRepository.save(kitapKullanici);
    }

    public List<KitapKullaniciRes> teslimEdilmeyenler(Long kutuphaneAccountId){
        Kutuphane kutuphane = kutuphaneRepository.findByAccountId(kutuphaneAccountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.teslimEdilmeyenler(kutuphane.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);

    }

    @Transactional
    public void deleteById(Long id) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kullanici(kullaniciRepository, kitapKullanici.getKullanici().getId());
        kitapKullaniciRepository.deleteById(id);
    }

}
