package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.entity.dto.request.*;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.entity.mapper.KitapMapper;
import com.sametozkan.kutuphane.entity.mapper.KitapYazarMapper;
import com.sametozkan.kutuphane.entity.model.Kitap;
import com.sametozkan.kutuphane.entity.model.Tur;
import com.sametozkan.kutuphane.entity.model.Yazar;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitapService {

    private final KitapRepository kitapRepository;
    private final KitapMapper kitapMapper;
    private final KitapYazarMapper kitapYazarMapper;
    private final YazarService yazarService;
    private final KitapYazarService kitapYazarService;
    private final TurService turService;
    private final KitapTurService kitapTurService;

    @Transactional
    public void save(KitapReq kitapReq) {
        kitapRepository.save(kitapMapper.convertToEntity(kitapReq));
    }

    @Transactional
    public KitapRes update(Long id, KitapReq kitapReq) {
        Kitap kitap = kitapRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kitap.setAdi(kitapReq.getAdi());
        kitap.setIsbn(kitapReq.getIsbn());
        kitap.setAciklama(kitapReq.getAciklama());
        kitap.setYayinYili(kitapReq.getYayinYili());

        if (kitapReq.getOtomatikOlusturuldu()) {
            kitap.setOtomatikOlusturuldu(true);
            kitap.setChatgptYorumu(kitapReq.getChatgptYorumu());
            kitap.setChatgptPuani(kitapReq.getChatgptPuani());
        } else {
            kitap.setOtomatikOlusturuldu(false);
            kitap.setChatgptYorumu(null);
            kitap.setChatgptPuani(null);
        }

        kitapRepository.save(kitap);
        return kitapMapper.convertToResponse(kitap);
    }

    public List<KitapRes> findAll() {
        return kitapMapper.convertToResponse(kitapRepository.findAll());
    }

    public KitapRes findById(Long id) {
        Kitap kitap = kitapRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kitapMapper.convertToResponse(kitap);
    }
}
