package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "yazar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Yazar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "adi", nullable = false)
    private String adi;

    @Column(name = "soyadi", nullable = false)
    private String soyadi;

    @OneToMany(mappedBy = "yazar")
    List<KitapYazar> kitaplar;
}
