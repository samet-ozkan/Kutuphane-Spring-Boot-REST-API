package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kitap_tur")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KitapTur extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kitap_id", nullable = false)
    private Kitap kitap;

    @ManyToOne
    @JoinColumn(name = "tur_id", nullable = false)
    private Tur tur;
}
