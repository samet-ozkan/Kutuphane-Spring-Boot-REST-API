package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.KutuphaneYorumReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneYorumRes;
import com.sametozkan.kutuphane.entity.mapper.KutuphaneYorumMapper;
import com.sametozkan.kutuphane.entity.model.KutuphaneYorum;
import com.sametozkan.kutuphane.entity.repository.KutuphaneYorumRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KutuphaneYorumService {

    private final KutuphaneYorumRepository kutuphaneYorumRepository;
    private final KutuphaneYorumMapper kutuphaneYorumMapper;

    @Transactional
    public KutuphaneYorum save(KutuphaneYorumReq kutuphaneYorumReq) {
        return kutuphaneYorumRepository.save(kutuphaneYorumMapper.convertToEntity(kutuphaneYorumReq));
    }

    public List<KutuphaneYorumRes> findByKutuphaneId(Long kutuphaneId) {
        Optional<List<KutuphaneYorum>> kutuphaneYorumList = kutuphaneYorumRepository.findByKutuphaneId(kutuphaneId);

        return kutuphaneYorumList.map(kutuphaneYorumMapper::convertToResponse).orElse(new ArrayList<>());
    }

}
