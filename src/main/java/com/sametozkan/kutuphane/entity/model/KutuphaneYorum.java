package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kutuphane_yorum")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KutuphaneYorum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "yorum")
    private String yorum;

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @ManyToOne
    @JoinColumn(name = "kutuphane_id", nullable = false)
    private Kutuphane kutuphane;
}
