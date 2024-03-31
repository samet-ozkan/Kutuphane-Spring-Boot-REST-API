package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.KitapKutuphane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitapKutuphaneRepository extends JpaRepository<KitapKutuphane, Long> {
}
