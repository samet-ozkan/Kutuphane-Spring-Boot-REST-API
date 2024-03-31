package com.sametozkan.kutuphane.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tur extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tur", nullable = false, unique = true)
    private String tur;

    @OneToMany(mappedBy = "tur")
    List<KitapTur> kitaplar;

}
