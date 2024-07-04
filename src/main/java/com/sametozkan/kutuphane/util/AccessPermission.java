package com.sametozkan.kutuphane.util;

import com.sametozkan.kutuphane.config.jwt.UserDetailsImpl;
import com.sametozkan.kutuphane.entity.model.Kullanici;
import com.sametozkan.kutuphane.entity.model.Kutuphane;
import com.sametozkan.kutuphane.entity.repository.KullaniciRepository;
import com.sametozkan.kutuphane.entity.repository.KutuphaneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public final class AccessPermission {
    public static boolean kullanici(KullaniciRepository kullaniciRepository, Long kullaniciId) {
        UserDetailsImpl authenticatedUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!authenticatedUser.getAuthorities().contains("ROLE_ADMIN")) {
            Kullanici kullanici = kullaniciRepository.findByAccountId(authenticatedUser.getId()).orElseThrow(EntityNotFoundException::new);
            if (!Objects.equals(kullanici.getId(), kullaniciId)) {
                System.out.println("Erişim izni yok!: " + kullanici.getId() + "  " + kullaniciId);
                throw new AccessDeniedException("Erişim izni yok.");
            }
        }
        return true;
    }

    public static boolean kutuphane(KutuphaneRepository kutuphaneRepository, Long kutuphaneId) {
        UserDetailsImpl authenticatedUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = authenticatedUser.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            Kutuphane kutuphane = kutuphaneRepository.findByAccountId(authenticatedUser.getId()).orElseThrow(EntityNotFoundException::new);
            if (!Objects.equals(kutuphane.getId(), kutuphaneId)) {
                throw new AccessDeniedException("Erişim izni yok.");
            }
        }
        return true;
    }

}
