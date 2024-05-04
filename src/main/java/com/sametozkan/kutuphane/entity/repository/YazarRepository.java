package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Yazar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YazarRepository extends JpaRepository<Yazar, Long> {

    @Query(value = "SELECT * FROM yazar WHERE lower(adi) = lower(:adi) AND lower(soyadi) = lower(:soyadi)", nativeQuery = true)
    Optional<Yazar> findByAdiAndSoyadi(String adi, String soyadi);

}
