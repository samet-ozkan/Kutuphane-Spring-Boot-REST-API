package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.KitapKutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKutuphaneRes;
import com.sametozkan.kutuphane.entity.mapper.KitapKutuphaneMapper;
import com.sametozkan.kutuphane.entity.model.KitapKutuphane;
import com.sametozkan.kutuphane.entity.repository.KitapKutuphaneRepository;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitapKutuphaneService {

    private final KitapKutuphaneMapper kitapKutuphaneMapper;
    private final KitapKutuphaneRepository kitapKutuphaneRepository;
    private final KitapRepository kitapRepository;
    private final KutuphaneRepository kutuphaneRepository;

    @Transactional
    public void save(KitapKutuphaneReq kitapKutuphaneReq) {
        kitapKutuphaneRepository.save(kitapKutuphaneMapper.convertToEntity(kitapKutuphaneReq));
    }

    public void update(Long id, KitapKutuphaneReq kitapKutuphaneReq) {
        KitapKutuphane kitapKutuphane = kitapKutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kitapKutuphane.setKitap(kitapRepository.getReferenceById(kitapKutuphaneReq.getKitap_id()));
        kitapKutuphane.setKutuphane(kutuphaneRepository.getReferenceById(kitapKutuphaneReq.getKutuphane_id()));
        kitapKutuphane.setStok(kitapKutuphaneReq.getStok());

        kitapKutuphaneRepository.save(kitapKutuphane);
    }

    public List<KitapKutuphaneRes> findAll() {
        return kitapKutuphaneMapper.convertToResponse(kitapKutuphaneRepository.findAll().stream().collect(Collectors.toList()));
    }

    public KitapKutuphaneRes findById(Long id) {
        KitapKutuphane kitapKutuphane = kitapKutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kitapKutuphaneMapper.convertToResponse(kitapKutuphane);
    }
}
