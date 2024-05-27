package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

    @Query(value = "SELECT * FROM kullanici WHERE account_id = :accountId", nativeQuery = true)
    Optional<Kullanici> findByAccountID(Long accountId);
}
