package com.sametozkan.kutuphane.entity.model;

import com.sametozkan.kutuphane.entity.dto.request.AccountReq;
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

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "kullanici")
    private List<KitapKullanici> kitaplar;
}
