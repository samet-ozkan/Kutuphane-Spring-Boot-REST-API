package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Kutuphane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KutuphaneRepository extends JpaRepository<Kutuphane, Long> {

    @Query(value = "SELECT * FROM kutuphane WHERE account_id = :accountId", nativeQuery = true)
    Optional<Kutuphane> findByAccountId(Long accountId);

}
