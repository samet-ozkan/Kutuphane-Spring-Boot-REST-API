package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.TurReq;
import com.sametozkan.kutuphane.entity.dto.response.TurRes;
import com.sametozkan.kutuphane.entity.mapper.TurMapper;
import com.sametozkan.kutuphane.entity.model.Tur;
import com.sametozkan.kutuphane.entity.repository.TurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void update(Long id, TurReq turReq) {
        Tur tur = turRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        tur.setTur(turReq.getTur());

        turRepository.save(tur);
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
