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

import java.util.*;

@Service
@RequiredArgsConstructor
public class YazarService {

    private final YazarRepository yazarRepository;
    private final YazarMapper yazarMapper;

    @Transactional
    public Yazar save(YazarReq yazarReq) {
        return yazarRepository.save(yazarMapper.convertToEntity(yazarReq));
    }

    public List<Yazar> saveAll(List<YazarReq> yazarReqList) {
        List<Yazar> yazarlar = new ArrayList<>();
        try {
            Iterator<YazarReq> iterator = yazarReqList.iterator();
            while (iterator.hasNext()) {
                YazarReq yazarReq = iterator.next();
                Optional<Yazar> yazar = findByAdiAndSoyadi(yazarReq.getAdi(), yazarReq.getSoyadi());
                if (yazar.isPresent()) {
                    yazarlar.add(yazar.get());
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!yazarReqList.isEmpty()) {
            List<Yazar> savedYazarlar = yazarRepository.saveAll(yazarMapper.convertToEntity(yazarReqList));
            yazarlar.addAll(savedYazarlar);
        }
        return yazarlar;
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
