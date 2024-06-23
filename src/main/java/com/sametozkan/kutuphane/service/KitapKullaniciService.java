package com.sametozkan.kutuphane.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sametozkan.kutuphane.config.chatgpt.ChatRequest;
import com.sametozkan.kutuphane.config.chatgpt.ChatResponse;
import com.sametozkan.kutuphane.config.chatgpt.GptClient;
import com.sametozkan.kutuphane.entity.dto.request.KitapKullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.dto.response.KitapOnerisiRes;
import com.sametozkan.kutuphane.entity.mapper.KitapKullaniciMapper;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.model.Kutuphane;
import com.sametozkan.kutuphane.entity.repository.KitapKullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KitapRepository;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import com.sametozkan.kutuphane.util.AccessPermission;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitapKullaniciService {

    private final KitapKullaniciRepository kitapKullaniciRepository;
    private final KitapRepository kitapRepository;
    private final KullaniciRepository kullaniciRepository;
    private final KutuphaneRepository kutuphaneRepository;
    private final KitapKullaniciMapper kitapKullaniciMapper;
    private final GptClient gptClient;

    @Transactional
    public void save(KitapKullaniciReq kitapKullaniciReq) {
        kitapKullaniciRepository.save(kitapKullaniciMapper.convertToEntity(kitapKullaniciReq));
    }

    @Transactional
    public KitapKullaniciRes update(Long id, KitapKullaniciReq kitapKullaniciReq) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        kitapKullanici.setKitap(kitapRepository.getReferenceById(kitapKullaniciReq.getKitapId()));
        kitapKullanici.setKullanici(kullaniciRepository.getReferenceById(kitapKullaniciReq.getKullaniciAccountId()));
        kitapKullanici.setKutuphane(kutuphaneRepository.getReferenceById(kitapKullaniciReq.getKutuphaneId()));
        kitapKullanici.setIadeDurumu(kitapKullaniciReq.getIadeDurumu());

        kitapKullaniciRepository.save(kitapKullanici);
        return kitapKullaniciMapper.convertToResponse(kitapKullanici);
    }

    public List<KitapKullaniciRes> findAll() {
        return kitapKullaniciRepository.findAll().stream().map(kitapKullaniciMapper::convertToResponse).toList();
    }

    public KitapKullaniciRes findById(Long id) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullanici);
    }

    public List<KitapKullaniciRes> findByKullaniciIdAndOnaylandiIsNull(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndOnaylandiIsNull(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapKullaniciRes> findByKullaniciIdAndOnaylandiIsFalse(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndOnaylandiIsFalse(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapKullaniciRes> findByKullaniciIdAndOnaylandiIsTrue(Long accountId) {
        Kullanici kullanici = kullaniciRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndOnaylandiIsTrue(kullanici.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapKullaniciRes> findByKutuphaneId(Long accountId) {
        Kutuphane kutuphane = kutuphaneRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKutuphaneId(kutuphane.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public void kitapIstegiOnayla(Long kitapKullaniciId) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(kitapKullaniciId).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kutuphane(kutuphaneRepository, kitapKullanici.getKutuphane().getId());
        kitapKullanici.setOnaylandi(true);
        kitapKullanici.setAlimTarihi(LocalDateTime.now());
        kitapKullaniciRepository.save(kitapKullanici);
    }

    public void kitapIstegiReddet(Long kitapKullaniciId) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(kitapKullaniciId).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kutuphane(kutuphaneRepository, kitapKullanici.getKutuphane().getId());
        kitapKullanici.setOnaylandi(false);
        kitapKullaniciRepository.save(kitapKullanici);
    }

    public void teslimEdildi(Long kitapKullaniciId){
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(kitapKullaniciId).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kutuphane(kutuphaneRepository, kitapKullanici.getKutuphane().getId());
        kitapKullanici.setIadeDurumu(true);
        kitapKullanici.setTeslimTarihi(LocalDateTime.now());
        kitapKullaniciRepository.save(kitapKullanici);
    }

    public List<KitapKullaniciRes> teslimEdilmeyenler(Long kutuphaneAccountId){
        Kutuphane kutuphane = kutuphaneRepository.findByAccountId(kutuphaneAccountId).orElseThrow(EntityNotFoundException::new);
        List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.teslimEdilmeyenler(kutuphane.getId()).orElseThrow(EntityNotFoundException::new);
        return kitapKullaniciMapper.convertToResponse(kitapKullaniciList);
    }

    public List<KitapOnerisiRes> fetchKitapOnerileri(Long accountId) throws JsonProcessingException {
        Kullanici kullanici = kullaniciRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        try {
            List<KitapKullanici> kitapKullaniciList = kitapKullaniciRepository.findByKullaniciIdAndOnaylandiIsTrue(kullanici.getId()).orElseThrow(EntityNotFoundException::new);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInput = objectMapper.writeValueAsString(new KitapOnerisiRes());

            if (kitapKullaniciList.isEmpty()) {
                List<String> kitapBasliklari = kitapKullaniciList.stream().map(kitapKullanici -> kitapKullanici.getKitap().getAdi()).collect(Collectors.toList());
                Collections.shuffle(kitapBasliklari);
                kitapBasliklari = kitapBasliklari.subList(0, Math.min(kitapBasliklari.size(), 5));

                ChatResponse response = gptClient.chat(new ChatRequest(
                        "Bu kitap başlıklarını göz önüne alarak JSON yapısı " + jsonInput + " şeklinde olan bir JSON listesi oluştur. Bu listede 5 adet öneri bulunmalıdır: " + kitapBasliklari + " (onerilmeSebebi alanında ilgili kitabı neden önerdiğinden bahsetmeni istiyorum. Bu kısım boş olmamalıdır.')"
                ));
                String kitapOnerileriJSON = response.choices().get(0).message().content();
                List<KitapOnerisiRes> kitapOnerileri = objectMapper.readValue(kitapOnerileriJSON, new TypeReference<List<KitapOnerisiRes>>() {
                });
                return kitapOnerileri;
            } else {
                ChatResponse response = gptClient.chat(new ChatRequest(
                        "Bu şekilde JSON yapısında olan " + jsonInput + " JSON listesi halinde 5 adet kitap öner (Yanıtın parse edilebilecek JSON şeklinde olsun): "
                ));
                String kitapOnerileriJSON = response.choices().get(0).message().content();
                List<KitapOnerisiRes> kitapOnerileri = objectMapper.readValue(kitapOnerileriJSON, new TypeReference<List<KitapOnerisiRes>>() {
                });
                return kitapOnerileri;
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(Long id) {
        KitapKullanici kitapKullanici = kitapKullaniciRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        AccessPermission.kullanici(kullaniciRepository, kitapKullanici.getKutuphane().getId());
        kitapKullaniciRepository.deleteById(id);
    }

}
