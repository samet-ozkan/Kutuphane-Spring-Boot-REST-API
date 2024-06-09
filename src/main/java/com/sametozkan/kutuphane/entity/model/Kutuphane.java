package com.sametozkan.kutuphane.entity.model;

import com.sametozkan.kutuphane.entity.dto.request.AccountReq;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "kutuphane")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kutuphane extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "adi", nullable = false)
    private String adi;

    @Column(name = "adresi", nullable = false)
    private String adresi;

    @Column(name = "sehir", nullable = false)
    private String sehir;

    @Column(name = "teslim_suresi", nullable = false)
    private Integer teslimSuresi;

    @Column(name = "chatgpt_yorumu", nullable = false)
    private String chatGptYorumu;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "kutuphane")
    private List<KitapKutuphane> kitaplar;

    @OneToMany(mappedBy = "kutuphane")
    private List<KitapKullanici> kullanicilar;

    @OneToMany(mappedBy = "kutuphane")
    private List<KutuphaneYorum> yorumlar;
}
