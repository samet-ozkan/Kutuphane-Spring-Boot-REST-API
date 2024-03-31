package com.sametozkan.kutuphane.listener;

import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class KitapKullaniciListener {

    private KutuphaneRepository kutuphaneRepository;

    @PrePersist
    public void prePersist(KitapKullanici kitapKullanici) {
        kitapKullanici.setAlimTarihi(LocalDateTime.now());
        Integer teslimSuresi = kutuphaneRepository.getReferenceById(kitapKullanici.getKutuphane().getId()).getTeslimSuresi();
        kitapKullanici.setTeslimTarihi(LocalDateTime.now().plusDays(teslimSuresi));
    }
}
