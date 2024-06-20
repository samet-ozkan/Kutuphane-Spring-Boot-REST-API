package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query(value = "SELECT * FROM refresh_token WHERE token = :token", nativeQuery = true)
    Optional<RefreshToken> findByToken(String token);

    @Query(value = "DELETE FROM refresh_token WHERE account_id = :accountId", nativeQuery = true)
    void deleteByAccountId(Long accountId);
}
