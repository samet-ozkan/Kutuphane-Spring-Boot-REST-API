package com.sametozkan.kutuphane.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sametozkan.kutuphane.config.googlebooks.BookResponse;
import com.sametozkan.kutuphane.config.googlebooks.BooksClient;
import com.sametozkan.kutuphane.config.googlebooks.VolumeInfo;
import com.sametozkan.kutuphane.entity.dto.request.*;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.entity.mapper.KitapMapper;
import com.sametozkan.kutuphane.entity.mapper.KitapYazarMapper;
import com.sametozkan.kutuphane.entity.model.Kitap;
import com.sametozkan.kutuphane.entity.model.KitapTur;
import com.sametozkan.kutuphane.entity.model.Tur;
import com.sametozkan.kutuphane.entity.model.Yazar;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.util.Utils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private final BooksClient booksClient;

    @Transactional
    public Long save(KitapReq kitapReq) {
        Long kitapId = kitapRepository.save(kitapMapper.convertToEntity(kitapReq)).getId();
        List<Yazar> yazarlar = new ArrayList<>(yazarService.saveAll(kitapReq.getYazarlar()));
        List<KitapYazarReq> kitapYazarReqList = new ArrayList<>();
        yazarlar.forEach(yazar -> {
            kitapYazarReqList.add(new KitapYazarReq(kitapId, yazar.getId()));
        });
        kitapYazarService.saveAll(kitapYazarReqList);
        List<Tur> turler = turService.saveAll(kitapReq.getTurler());
        List<KitapTurReq> kitapTurReqList = new ArrayList<>();
        turler.forEach(tur -> {
            kitapTurReqList.add(new KitapTurReq(kitapId, tur.getId()));
        });
        kitapTurService.saveAll(kitapTurReqList);
        return kitapId;
    }

    @Transactional
    public KitapRes update(Long id, KitapReq kitapReq) {
        Kitap kitap = kitapRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kitap.setAdi(kitapReq.getAdi());
        kitap.setIsbn(kitapReq.getIsbn());
        kitap.setAciklama(kitapReq.getAciklama());
        kitap.setYayinTarihi(kitapReq.getYayinTarihi());
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

    public KitapRes findByIsbn(Long isbn) {
        Optional<Kitap> kitap = kitapRepository.findByIsbn(isbn);
        if (kitap.isPresent()) {
            return kitapMapper.convertToResponse(kitap.get());
        } else {
            return null;
        }
    }

    public KitapReq fetchByIsbn(Long isbn) {
        String booksApiResponse = booksClient.getByIsbn(isbn);
        Gson gson = new Gson();
        BookResponse bookResponse = gson.fromJson(booksApiResponse, BookResponse.class);
        if (bookResponse.getTotalItems() != null && !bookResponse.getTotalItems().equals(0)) {
            VolumeInfo volumeInfo = bookResponse.getItems().get(0).volumeInfo();
            List<YazarReq> yazarlar = new ArrayList<>();
            List<TurReq> turler = new ArrayList<>();
            if (volumeInfo.authors() != null && !volumeInfo.authors().isEmpty()) {
                volumeInfo.authors().forEach(fullName ->
                        yazarlar.add(new YazarReq(Utils.getFirstName(fullName), Utils.getLastName(fullName))));
            }
            if (volumeInfo.categories() != null && !volumeInfo.categories().isEmpty()) {
                volumeInfo.categories().forEach(tur -> turler.add(new TurReq(tur)));
            }
            KitapReq kitapReq = KitapReq.builder()
                    .isbn(isbn)
                    .adi(volumeInfo.title())
                    .yayinTarihi(volumeInfo.publishedDate())
                    .dil(volumeInfo.language())
                    .sayfaSayisi(volumeInfo.pageCount())
                    .aciklama(volumeInfo.description())
                    .yazarlar(yazarlar)
                    .turler(turler)
                    .build();
            return kitapReq;
        }
        return null;
    }
}
