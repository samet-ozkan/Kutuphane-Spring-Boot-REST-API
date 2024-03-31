package com.sametozkan.kutuphane.entity.model;

import com.sametozkan.kutuphane.entity.dto.request.KitapKutuphaneReq;
import com.sametozkan.kutuphane.entity.dto.request.KitapReq;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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

    @Column(name = "yayin_yili", nullable = false)
    private Integer yayinYili;

    @Column(name = "aciklama", nullable = false)
    private String aciklama;

    @Column(name = "chatgpt_puani")
    private Double chatgptPuani;

    @Column(name = "chatgpt_yorumu")
    private String chatgptYorumu;

    @OneToMany(mappedBy = "kitap")
    List<KitapYazar> yazarlar;

    @OneToMany(mappedBy = "kitap")
    List<KitapTur> turler;

    @OneToMany(mappedBy = "kitap")
    List<KitapKutuphane> kutuphaneler;

    @OneToMany(mappedBy = "kitap")
    List<KitapKullanici> kullanicilar;

    @Column(name = "otomatik_olusturuldu")
    private Boolean otomatikOlusturuldu;

}
