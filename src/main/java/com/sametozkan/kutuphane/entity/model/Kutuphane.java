package com.sametozkan.kutuphane.entity.model;

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

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "teslim_suresi", nullable = false)
    private Integer teslimSuresi;

    @OneToMany(mappedBy = "kutuphane")
    private List<KitapKutuphane> kitaplar;

    @OneToMany(mappedBy = "kutuphane")
    private List<KitapKullanici> kullanicilar;
}
