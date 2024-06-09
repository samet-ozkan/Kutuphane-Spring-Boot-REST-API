package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kitap_yorum")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KitapYorum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "yorum", nullable = false)
    private String yorum;

    @Column(name = "spoiler")
    private Boolean spoiler;

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @ManyToOne
    @JoinColumn(name = "kitap_id", nullable = false)
    private Kitap kitap;
}

