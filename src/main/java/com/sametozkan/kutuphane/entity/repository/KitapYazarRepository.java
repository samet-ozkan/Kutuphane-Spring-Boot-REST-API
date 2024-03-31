package com.sametozkan.kutuphane.entity.repository;

import com.sametozkan.kutuphane.entity.model.KitapYazar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitapYazarRepository extends JpaRepository<KitapYazar, Long> {

}
