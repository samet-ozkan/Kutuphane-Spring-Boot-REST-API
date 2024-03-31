package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.KitapTur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitapTurRepository extends JpaRepository<KitapTur, Long> {
}
