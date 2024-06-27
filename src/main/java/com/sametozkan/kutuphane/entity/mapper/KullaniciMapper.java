package com.sametozkan.kutuphane.entity.mapper;

import com.sametozkan.kutuphane.entity.dto.request.KullaniciReq;
import com.sametozkan.kutuphane.entity.dto.response.KullaniciRes;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.repository.AccountRepository;
import com.sametozkan.kutuphane.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KullaniciMapper {

    private final KitapMapper kitapMapper;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final DateUtil dateUtil;

    public Kullanici convertToEntity(KullaniciReq kullaniciReq) {

        return Kullanici.builder()
                .adi(kullaniciReq.getAdi())
                .soyadi(kullaniciReq.getSoyadi())
                .adres(kullaniciReq.getAdres())
                .telefonNumarasi(kullaniciReq.getTelefonNumarasi())
                .dogumTarihi(dateUtil.convertToLocalDate(kullaniciReq.getDogumTarihi()))
                .build();
    }

    public KullaniciRes convertToResponse(Kullanici kullanici) {
        return KullaniciRes.builder()
                .id(kullanici.getId())
                .adi(kullanici.getAdi())
                .soyadi(kullanici.getSoyadi())
                .adres(kullanici.getAdres())
                .telefonNumarasi(kullanici.getTelefonNumarasi())
                .dogumTarihi(dateUtil.toString(kullanici.getDogumTarihi()))
                .account(accountMapper.convertToResponse(kullanici.getAccount()))
                .kitaplar(kitapMapper.convertToResponse(kullanici.getKitaplar().stream().map(KitapKullanici::getKitap).toList()))
                .build();
    }

    public List<KullaniciRes> convertToResponse(List<Kullanici> kullanicilar) {
        return kullanicilar.stream().map(this::convertToResponse).toList();
    }
}
