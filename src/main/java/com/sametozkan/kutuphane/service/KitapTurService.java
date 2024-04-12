package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.KitapTurReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapTurRes;
import com.sametozkan.kutuphane.entity.mapper.KitapTurMapper;
import com.sametozkan.kutuphane.entity.model.KitapTur;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KitapTurRepository;
import com.sametozkan.kutuphane.entity.repository.TurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitapTurService {

    private final KitapTurRepository kitapTurRepository;
    private final KitapTurMapper kitapTurMapper;
    private final TurRepository turRepository;
    private final KitapRepository kitapRepository;

    @Transactional
    public void save(KitapTurReq kitapTurReq) {
        kitapTurRepository.save(kitapTurMapper.convertToEntity(kitapTurReq));
    }

    @Transactional
    public KitapTurRes update(Long id, KitapTurReq kitapTurReq) {
        KitapTur kitapTur = kitapTurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kitapTur.setTur(turRepository.getReferenceById(kitapTurReq.getTur_id()));
        kitapTur.setKitap(kitapRepository.getReferenceById(kitapTurReq.getKitap_id()));

        kitapTurRepository.save(kitapTur);
        return kitapTurMapper.convertToResponse(kitapTur);
    }

    public List<KitapTurRes> findAll() {
        return kitapTurMapper.convertToResponse(kitapTurRepository.findAll());
    }

    public KitapTurRes findById(Long id) {
        KitapTur kitapTur = kitapTurRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kitapTurMapper.convertToResponse(kitapTur);
    }
}
