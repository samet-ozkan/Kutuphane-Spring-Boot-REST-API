package com.sametozkan.kutuphane.entity.model;

import com.sametozkan.kutuphane.entity.dto.request.AccountReq;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "kullanici")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kullanici extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "adi", nullable = false)
    private String adi;

    @Column(name = "soyadi", nullable = false)
    private String soyadi;

    @Column(name = "adres", nullable = false)
    private String adres;

    @Column(name = "telefon_numarasi", nullable = false)
    private String telefonNumarasi;

    @Column(name = "dogum_tarihi", nullable = false)
    private LocalDate dogumTarihi;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "kullanici")
    private List<KitapKullanici> kitaplar;
}
