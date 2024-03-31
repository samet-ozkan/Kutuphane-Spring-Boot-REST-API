package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class TurRes {
    private Long id;
    private String tur;
    private List<KitapRes> kitaplar;
}
