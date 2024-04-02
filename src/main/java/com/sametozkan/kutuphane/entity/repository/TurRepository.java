package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Tur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurRepository extends JpaRepository<Tur, Long> {

    @Query(value = "SELECT * FROM tur WHERE lower(tur) = lower(:tur)", nativeQuery = true)
    Optional<Tur> findByTur(String tur);
}
