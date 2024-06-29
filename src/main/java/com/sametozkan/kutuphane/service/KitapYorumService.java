package com.sametozkan.kutuphane.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sametozkan.kutuphane.entity.dto.request.KitapYorumReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapYorumRes;
import com.sametozkan.kutuphane.entity.mapper.KitapYorumMapper;
import com.sametozkan.kutuphane.entity.model.KitapYorum;
import com.sametozkan.kutuphane.entity.repository.KitapYorumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitapYorumService {

    private final KitapYorumRepository kitapYorumRepository;
    private final KitapYorumMapper kitapYorumMapper;

    @Transactional
    public KitapYorum save(KitapYorumReq kitapYorumReq) throws JsonProcessingException {
        return kitapYorumRepository.save(kitapYorumMapper.convertToEntity(kitapYorumReq));
    }

    public List<KitapYorumRes> findByKitapId(Long kitapId) {
        Optional<List<KitapYorum>> kitapYorumList = kitapYorumRepository.findByKitapId(kitapId);

        return kitapYorumList.map(kitapYorumMapper::convertToResponse).orElse(new ArrayList<>());
    }

}

