package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "kitap_kullanici")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KitapKullanici extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kitap_id", nullable = false)
    private Kitap kitap;

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @ManyToOne
    @JoinColumn(name = "kutuphane_id", nullable = false)
    private Kutuphane kutuphane;

    @Column(name = "alim_tarihi")
    private LocalDateTime alimTarihi;

    @Column(name = "teslim_tarihi")
    private LocalDateTime teslimTarihi;

    @Column(name = "iade_durumu")
    private Boolean iadeDurumu;
}
