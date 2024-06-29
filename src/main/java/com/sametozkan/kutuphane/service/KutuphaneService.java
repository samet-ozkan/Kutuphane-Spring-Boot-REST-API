package com.sametozkan.kutuphane.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sametozkan.kutuphane.entity.dto.request.KutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneRes;
import com.sametozkan.kutuphane.entity.mapper.KutuphaneMapper;
import com.sametozkan.kutuphane.entity.model.Account;
import com.sametozkan.kutuphane.entity.model.Kutuphane;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import com.sametozkan.kutuphane.util.AccessPermission;
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
    public void save(KutuphaneReq kutuphaneReq, Account account) throws JsonProcessingException {
        Kutuphane kutuphane = kutuphaneMapper.convertToEntity(kutuphaneReq);
        kutuphane.setAccount(account);
        kutuphaneRepository.save(kutuphane);
    }

    @Transactional
    public KutuphaneRes update(Long id, KutuphaneReq kutuphaneReq) {
        Kutuphane kutuphane = kutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kutuphane(kutuphaneRepository, id);
        kutuphane.setAdi(kutuphaneReq.getAdi());
        kutuphane.setAdresi(kutuphaneReq.getAdresi());
        kutuphane.setTeslimSuresi(kutuphaneReq.getTeslimSuresi());

        kutuphaneRepository.save(kutuphane);
        return kutuphaneMapper.convertToResponse(kutuphane);
    }

    public List<KutuphaneRes> findAll() {
        return kutuphaneMapper.convertToResponse(kutuphaneRepository.findAll());
    }

    public KutuphaneRes findById(Long id) {
        System.out.println("ID: " + id);
        Kutuphane kutuphane = kutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kutuphaneMapper.convertToResponse(kutuphane);
    }

    public KutuphaneRes findByAccountId(Long accountId) {
        Kutuphane kutuphane = kutuphaneRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        return kutuphaneMapper.convertToResponse(kutuphane);
    }
}
