package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "kullanici")
    private List<KitapKullanici> kitaplar;
}
