package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.config.jwt.UserDetailsImpl;
import com.sametozkan.kutuphane.entity.dto.request.KitapKutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKutuphaneRes;
import com.sametozkan.kutuphane.entity.mapper.KitapKutuphaneMapper;
import com.sametozkan.kutuphane.entity.model.KitapKutuphane;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.repository.KitapKutuphaneRepository;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import com.sametozkan.kutuphane.util.AccessPermission;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitapKutuphaneService {

    private final KitapKutuphaneMapper kitapKutuphaneMapper;
    private final KullaniciRepository kullaniciRepository;
    private final KitapKutuphaneRepository kitapKutuphaneRepository;
    private final KitapRepository kitapRepository;
    private final KutuphaneRepository kutuphaneRepository;

    @Transactional
    public void save(KitapKutuphaneReq kitapKutuphaneReq) {
        AccessPermission.kutuphane(kutuphaneRepository, kitapKutuphaneReq.getKutuphane_id());
        kitapKutuphaneRepository.save(kitapKutuphaneMapper.convertToEntity(kitapKutuphaneReq));
    }

    @Transactional
    public KitapKutuphaneRes update(Long id, KitapKutuphaneReq kitapKutuphaneReq) {
        AccessPermission.kutuphane(kutuphaneRepository, kitapKutuphaneReq.getKutuphane_id());
        KitapKutuphane kitapKutuphane = kitapKutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kitapKutuphane.setKitap(kitapRepository.getReferenceById(kitapKutuphaneReq.getKitap_id()));
        kitapKutuphane.setKutuphane(kutuphaneRepository.getReferenceById(kitapKutuphaneReq.getKutuphane_id()));

        kitapKutuphaneRepository.save(kitapKutuphane);
        return kitapKutuphaneMapper.convertToResponse(kitapKutuphane);
    }

    public List<KitapKutuphaneRes> findAll() {
        return kitapKutuphaneMapper.convertToResponse(kitapKutuphaneRepository.findAll().stream().collect(Collectors.toList()));
    }

    public KitapKutuphaneRes findById(Long id) {
        KitapKutuphane kitapKutuphane = kitapKutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kitapKutuphaneMapper.convertToResponse(kitapKutuphane);
    }

}
