package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Kutuphane;
import com.sametozkan.kutuphane.entity.model.KutuphaneYorum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KutuphaneYorumRepository extends JpaRepository<KutuphaneYorum, Long> {

    @Query(value = "SELECT * FROM kutuphane_yorum WHERE kutuphane_id = :kutuphaneId", nativeQuery = true)
    Optional<List<KutuphaneYorum>> findByKutuphaneId(Long kutuphaneId);
}
