package com.sametozkan.kutuphane.service;

import com.google.gson.Gson;
import com.sametozkan.kutuphane.config.chatgpt.ChatRequest;
import com.sametozkan.kutuphane.config.chatgpt.ChatResponse;
import com.sametozkan.kutuphane.config.chatgpt.GptResponse;
import com.sametozkan.kutuphane.config.googlebooks.BookResponse;
import com.sametozkan.kutuphane.config.googlebooks.BooksClient;
import com.sametozkan.kutuphane.config.googlebooks.VolumeInfo;
import com.sametozkan.kutuphane.entity.dto.request.*;
import com.sametozkan.kutuphane.entity.dto.response.KitapRes;
import com.sametozkan.kutuphane.entity.mapper.KitapMapper;
import com.sametozkan.kutuphane.entity.mapper.KitapYazarMapper;
import com.sametozkan.kutuphane.entity.model.Kitap;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void save(KitapReq kitapReq) {
        kitapRepository.save(kitapMapper.convertToEntity(kitapReq));
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

    public KitapReq fetchByIsbn(Long isbn) {
        String booksApiResponse = booksClient.getByIsbn(isbn);
        Gson gson = new Gson();
        BookResponse bookResponse = gson.fromJson(booksApiResponse, BookResponse.class);
        if (bookResponse.getTotalItems() != null && !bookResponse.getTotalItems().equals(0)) {
            VolumeInfo volumeInfo = bookResponse.getItems().get(0).volumeInfo();
            KitapReq kitapReq = KitapReq.builder()
                    .isbn(isbn)
                    .adi(volumeInfo.title())
                    .yayinTarihi(volumeInfo.publishedDate())
                    .dil(volumeInfo.language())
                    .sayfaSayisi(volumeInfo.pageCount())
                    .aciklama(volumeInfo.description())
                    .build();
            return kitapReq;
        }
        return null;
    }
}
