package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.YazarReq;
import com.sametozkan.kutuphane.entity.dto.response.YazarRes;
import com.sametozkan.kutuphane.entity.mapper.YazarMapper;
import com.sametozkan.kutuphane.entity.model.Yazar;
import com.sametozkan.kutuphane.entity.repository.YazarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class YazarService {

    private final YazarRepository yazarRepository;
    private final YazarMapper yazarMapper;

    @Transactional
    public Yazar save(YazarReq yazarReq) {
        return yazarRepository.save(yazarMapper.convertToEntity(yazarReq));
    }

    @Transactional
    public YazarRes update(Long id, YazarReq yazarReq) {
        Yazar yazar = yazarRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        yazar.setAdi(yazarReq.getAdi());
        yazar.setSoyadi(yazarReq.getSoyadi());

        yazarRepository.save(yazar);
        return yazarMapper.convertToResponse(yazar);
    }

    public List<YazarRes> findAll() {
        return yazarMapper.convertToResponse(yazarRepository.findAll());
    }

    public YazarRes findById(Long id) {
        Yazar yazar = yazarRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return yazarMapper.convertToResponse(yazar);
    }

    @Transactional
    public Optional<Yazar> findByAdiAndSoyadi(String adi, String soyadi) {
        return yazarRepository.findByAdiAndSoyadi(adi, soyadi);
    }
}
