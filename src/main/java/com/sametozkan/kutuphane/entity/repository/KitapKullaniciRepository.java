package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitapKullaniciRepository extends JpaRepository<KitapKullanici, Long> {

    @Query(value = "SELECT * FROM kitap_kullanici WHERE kullanici_id = :kullaniciId AND iade_durumu IS NULL", nativeQuery = true)
    Optional<List<KitapKullanici>> findByKullaniciIdAndIadeDurumuIsNull(@Param("kullaniciId") Long kullaniciId);

    @Query(value = "SELECT * FROM kitap_kullanici WHERE kullanici_id = :kullaniciId AND iade_durumu = false", nativeQuery = true)
    Optional<List<KitapKullanici>> findByKullaniciIdAndIadeDurumuIsFalse(@Param("kullaniciId") Long kullaniciId);

    @Query(value = "SELECT * FROM kitap_kullanici WHERE kullanici_id = :kullaniciId AND iade_durumu = true", nativeQuery = true)
    Optional<List<KitapKullanici>> findByKullaniciIdAndIadeDurumuIsTrue(@Param("kullaniciId") Long kullaniciId);

}
