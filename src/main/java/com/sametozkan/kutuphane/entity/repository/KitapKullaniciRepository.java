package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitapKullaniciRepository extends JpaRepository<KitapKullanici, Long> {
}
