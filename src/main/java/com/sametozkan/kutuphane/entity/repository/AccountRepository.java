package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM account WHERE email = :email", nativeQuery = true)
    Optional<Account> findByEmail(String email);
}
