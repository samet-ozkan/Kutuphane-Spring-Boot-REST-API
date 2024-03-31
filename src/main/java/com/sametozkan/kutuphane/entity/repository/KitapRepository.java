package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitapRepository extends JpaRepository<Kitap, Long> {
}
