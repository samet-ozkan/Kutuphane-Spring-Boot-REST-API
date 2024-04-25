package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "kitap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kitap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "isbn", nullable = false, unique = true)
    private Long isbn;

    @Column(name = "adi", nullable = false)
    private String adi;

    @Column(name = "yayin_tarihi", nullable = false)
    private String yayinTarihi;

    @Column(name = "dil", nullable = false)
    private String dil;

    @Column(name = "sayfa_sayisi", nullable = false)
    private Integer sayfaSayisi;

    @Column(name = "aciklama", nullable = false)
    private String aciklama;

    @OneToMany(mappedBy = "kitap")
    List<KitapYazar> yazarlar;

    @OneToMany(mappedBy = "kitap")
    List<KitapTur> turler;

    @OneToMany(mappedBy = "kitap")
    List<KitapKutuphane> kutuphaneler;

    @OneToMany(mappedBy = "kitap")
    List<KitapKullanici> kullanicilar;

}
