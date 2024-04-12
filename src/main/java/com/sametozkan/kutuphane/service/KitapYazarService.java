package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.KitapYazarReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapYazarRes;
import com.sametozkan.kutuphane.entity.mapper.KitapYazarMapper;
import com.sametozkan.kutuphane.entity.model.KitapYazar;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KitapYazarRepository;
import com.sametozkan.kutuphane.entity.repository.YazarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitapYazarService {

    private final KitapYazarMapper kitapYazarMapper;
    private final KitapYazarRepository kitapYazarRepository;
    private final KitapRepository kitapRepository;
    private final YazarRepository yazarRepository;

    @Transactional
    public void save(KitapYazarReq kitapYazarReq) {
        kitapYazarRepository.save(kitapYazarMapper.convertToEntity(kitapYazarReq));
    }

    @Transactional
    public KitapYazarRes update(Long id, KitapYazarReq kitapYazarReq) {
        KitapYazar kitapYazar = kitapYazarRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kitapYazar.setKitap(kitapRepository.getReferenceById(kitapYazarReq.getKitap_id()));
        kitapYazar.setYazar(yazarRepository.getReferenceById(kitapYazarReq.getYazar_id()));

        kitapYazarRepository.save(kitapYazar);
        return kitapYazarMapper.convertToResponse(kitapYazar);
    }

    public List<KitapYazarRes> findAll() {
        return kitapYazarMapper.convertToResponse(kitapYazarRepository.findAll());
    }

    public KitapYazarRes findById(Long id) {
        KitapYazar kitapYazar = kitapYazarRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kitapYazarMapper.convertToResponse(kitapYazar);
    }
}
