package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KitapRepository extends JpaRepository<Kitap, Long> {

    @Query(value = "SELECT * FROM kitap WHERE isbn = :isbn", nativeQuery = true)
    Optional<Kitap> findByIsbn(Long isbn);
}
