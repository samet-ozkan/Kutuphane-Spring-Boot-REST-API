package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.KitapYorum;
import com.sametozkan.kutuphane.entity.model.KutuphaneYorum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KitapYorumRepository extends JpaRepository<KitapYorum, Long> {

    @Query(value = "SELECT * FROM kitap_yorum WHERE kitap_id = :kitapId ORDER BY created_time DESC", nativeQuery = true)
    Optional<List<KitapYorum>> findByKitapId(Long kitapId);
}
