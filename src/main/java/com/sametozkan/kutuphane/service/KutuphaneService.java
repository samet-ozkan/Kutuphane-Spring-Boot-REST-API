package com.sametozkan.kutuphane.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sametozkan.kutuphane.config.chatgpt.ChatRequest;
import com.sametozkan.kutuphane.config.chatgpt.ChatResponse;
import com.sametozkan.kutuphane.config.chatgpt.GptClient;
import com.sametozkan.kutuphane.entity.dto.request.KitapReq;
import com.sametozkan.kutuphane.entity.dto.request.KutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneRes;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneYorumRes;
import com.sametozkan.kutuphane.entity.mapper.KutuphaneMapper;
import com.sametozkan.kutuphane.entity.mapper.KutuphaneYorumMapper;
import com.sametozkan.kutuphane.entity.model.Account;
import com.sametozkan.kutuphane.entity.model.Kutuphane;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
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
    private final GptClient gptClient;

    @Transactional
    public void save(KutuphaneReq kutuphaneReq, Account account) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInput = objectMapper.writeValueAsString(kutuphaneReq);

            ChatResponse response = gptClient.chat(new ChatRequest(
                    "Lütfen aşağıdaki JSON'daki yazım yanlışlarını düzeltin ve sonucu tekrar JSON formatında döndürün. Düzeltmelerde şu kurallara dikkat edin:" +
                            "\n1. Şehir kısmında kısaltma kullanılmışsa, uzun halini yazın." +
                            "\n2. Büyük/küçük harf kurallarına dikkat edin." +
                            "\n3. Noktalama işaretlerine dikkat edin." +
                            "\n4. Boş bir alan varsa doldurun." +
                            "\n5. ChatGptYorumu alanına ChatGPT'nin bu kütüphane ile ilgili düşüncelerini yazın." +
                            "\n\nDüzeltilecek JSON: " + jsonInput +
                            "\n\nLütfen düzeltilmiş JSON'u sadece JSON formatında döndürün."
            ));

            String correctedJson = response.choices().get(0).message().content();

            System.out.println("Kutuphane Json: " + correctedJson);

            kutuphaneReq = objectMapper.readValue(correctedJson, KutuphaneReq.class);

        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
            throw e;
        }

        Kutuphane kutuphane = kutuphaneMapper.convertToEntity(kutuphaneReq);
        kutuphane.setAccount(account);
        kutuphaneRepository.save(kutuphane);
    }

    @Transactional
    public KutuphaneRes update(Long id, KutuphaneReq kutuphaneReq) {
        Kutuphane kutuphane = kutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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
        Kutuphane kutuphane = kutuphaneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return kutuphaneMapper.convertToResponse(kutuphane);
    }

    public KutuphaneRes findByAccountId(Long accountId) {
        Kutuphane kutuphane = kutuphaneRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        return kutuphaneMapper.convertToResponse(kutuphane);
    }
}
