package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
}
