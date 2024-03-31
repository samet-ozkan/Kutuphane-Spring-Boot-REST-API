package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Tur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TurRepository extends JpaRepository<Tur, Long> {

    @Query("SELECT * FROM tur WHERE lower(tur) = lower(:tur)")
    Optional<Tur> findByTur(String tur);
}
