package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kitap_yazar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KitapYazar extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kitap_id", nullable = false)
    private Kitap kitap;

    @ManyToOne
    @JoinColumn(name = "yazar_id", nullable = false)
    private Yazar yazar;

}
