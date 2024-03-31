package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.KutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneRes;
import com.sametozkan.kutuphane.entity.mapper.KutuphaneMapper;
import com.sametozkan.kutuphane.entity.model.Kutuphane;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KutuphaneService {

    private final KutuphaneMapper kutuphaneMapper;
    private final KutuphaneRepository kutuphaneRepository;

    @Transactional
    public void save(KutuphaneReq kutuphaneReq) {
        kutuphaneRepository.save(kutuphaneMapper.convertToEntity(kutuphaneReq));
    }

    @Transactional
    public void update(Long id, KutuphaneReq kutuphaneReq) {
        Kutuphane kutuphane = kutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kutuphane.setAdi(kutuphaneReq.getAdi());
        kutuphane.setAdresi(kutuphaneReq.getAdresi());
        kutuphane.setEmail(kutuphaneReq.getEmail());
        kutuphane.setPassword(kutuphaneReq.getPassword());
        kutuphane.setTeslimSuresi(kutuphaneReq.getTeslimSuresi());

        kutuphaneRepository.save(kutuphane);
    }

    public List<KutuphaneRes> findAll() {
        return kutuphaneMapper.convertToResponse(kutuphaneRepository.findAll());
    }

    public KutuphaneRes findById(Long id) {
        Kutuphane kutuphane = kutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kutuphaneMapper.convertToResponse(kutuphane);
    }
}
