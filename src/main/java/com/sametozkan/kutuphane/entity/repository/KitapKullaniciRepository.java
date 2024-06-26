package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.dto.response.KitapKullaniciRes;
import com.sametozkan.kutuphane.entity.model.KitapKullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitapKullaniciRepository extends JpaRepository<KitapKullanici, Long> {

    @Query(value = "SELECT * FROM kitap_kullanici WHERE kullanici_id = :kullaniciId AND onaylandi IS NULL", nativeQuery = true)
    Optional<List<KitapKullanici>> findByKullaniciIdAndOnaylandiIsNull(@Param("kullaniciId") Long kullaniciId);

    @Query(value = "SELECT * FROM kitap_kullanici WHERE kullanici_id = :kullaniciId AND onaylandi = false", nativeQuery = true)
    Optional<List<KitapKullanici>> findByKullaniciIdAndOnaylandiIsFalse(@Param("kullaniciId") Long kullaniciId);

    @Query(value = "SELECT * FROM kitap_kullanici WHERE kullanici_id = :kullaniciId AND onaylandi = true", nativeQuery = true)
    Optional<List<KitapKullanici>> findByKullaniciIdAndOnaylandiIsTrue(@Param("kullaniciId") Long kullaniciId);

    @Query(value = "SELECT * FROM kitap_kullanici WHERE kutuphane_id = :kutuphaneId", nativeQuery = true)
    Optional<List<KitapKullanici>> findByKutuphaneId(@Param("kutuphaneId") Long kutuphaneId);

    @Query(value = "SELECT * FROM kitap_kullanici WHERE kutuphane_id = :kutuphaneId AND onaylandi = true AND iade_durumu = false", nativeQuery = true)
    Optional<List<KitapKullanici>> teslimEdilmeyenler(@Param("kutuphaneId") Long kutuphaneId);
}
