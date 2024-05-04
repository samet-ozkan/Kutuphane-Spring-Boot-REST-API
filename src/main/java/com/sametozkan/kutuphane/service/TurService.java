package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.TurReq;
import com.sametozkan.kutuphane.entity.dto.request.YazarReq;
import com.sametozkan.kutuphane.entity.dto.response.TurRes;
import com.sametozkan.kutuphane.entity.mapper.TurMapper;
import com.sametozkan.kutuphane.entity.model.Tur;
import com.sametozkan.kutuphane.entity.model.Yazar;
import com.sametozkan.kutuphane.entity.repository.TurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurService {

    private final TurMapper turMapper;
    private final TurRepository turRepository;

    @Transactional
    public Tur save(TurReq turReq) {
        return turRepository.save(turMapper.convertToEntity(turReq));
    }

    public List<Tur> saveAll(List<TurReq> turReqList) {
        List<Tur> turler = new ArrayList<>();
        try {
            Iterator<TurReq> iterator = turReqList.iterator();
            while (iterator.hasNext()) {
                TurReq turReq = iterator.next();
                Optional<Tur> tur = findByTur(turReq.getTur());
                if (tur.isPresent()) {
                    turler.add(tur.get());
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!turReqList.isEmpty()) {
            List<Tur> savedTurler = turRepository.saveAll(turMapper.convertToEntity(turReqList));
            turler.addAll(savedTurler);
        }
        return turler;
    }


    @Transactional
    public TurRes update(Long id, TurReq turReq) {
        Tur tur = turRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        tur.setTur(turReq.getTur());

        turRepository.save(tur);
        return turMapper.convertToResponse(tur);
    }

    public List<TurRes> findAll() {
        return turMapper.convertToResponse(turRepository.findAll());
    }

    public TurRes findById(Long id) {
        Tur tur = turRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return turMapper.convertToResponse(tur);
    }

    public Optional<Tur> findByTur(String tur) {
        return turRepository.findByTur(tur);
    }
}
