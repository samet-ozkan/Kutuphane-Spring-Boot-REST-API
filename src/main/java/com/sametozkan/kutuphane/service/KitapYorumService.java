package com.sametozkan.kutuphane.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sametozkan.kutuphane.config.chatgpt.ChatRequest;
import com.sametozkan.kutuphane.config.chatgpt.ChatResponse;
import com.sametozkan.kutuphane.config.chatgpt.GptClient;
import com.sametozkan.kutuphane.entity.dto.request.KitapYorumReq;
import com.sametozkan.kutuphane.entity.dto.request.KutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.request.KutuphaneYorumReq;
import com.sametozkan.kutuphane.entity.dto.response.KitapYorumRes;
import com.sametozkan.kutuphane.entity.dto.response.KutuphaneYorumRes;
import com.sametozkan.kutuphane.entity.mapper.KitapYorumMapper;
import com.sametozkan.kutuphane.entity.mapper.KutuphaneYorumMapper;
import com.sametozkan.kutuphane.entity.model.Kitap;
import com.sametozkan.kutuphane.entity.model.KitapYorum;
import com.sametozkan.kutuphane.entity.model.KutuphaneYorum;
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
    private final GptClient gptClient;

    @Transactional
    public KitapYorum save(KitapYorumReq kitapYorumReq) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInput = objectMapper.writeValueAsString(kitapYorumReq);

            ChatResponse response = gptClient.chat(new ChatRequest(
                    "Lütfen aşağıdaki JSON'daki spoiler alanını doldur. Düzeltmelerde şu kurallara dikkat et:" +
                            "\n1. Yorum spoiler içeriyorsa spoiler alanının değeri true, içermiyorsa false olmalı." +
                            "\n\nDüzeltilecek JSON: " + jsonInput +
                            "\n\nLütfen düzeltilmiş JSON'u sadece JSON formatında döndür."
            ));

            String correctedJson = response.choices().get(0).message().content();

            kitapYorumReq = objectMapper.readValue(correctedJson, KitapYorumReq.class);

        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
            throw e;
        }
        return kitapYorumRepository.save(kitapYorumMapper.convertToEntity(kitapYorumReq));
    }

    public List<KitapYorumRes> findByKitapId(Long kitapId) {
        Optional<List<KitapYorum>> kitapYorumList = kitapYorumRepository.findByKitapId(kitapId);

        return kitapYorumList.map(kitapYorumMapper::convertToResponse).orElse(new ArrayList<>());
    }

}

