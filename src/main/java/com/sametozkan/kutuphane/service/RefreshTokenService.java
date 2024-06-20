package com.sametozkan.kutuphane.service;

import com.sametozkan.kutuphane.config.jwt.JwtUtils;
import com.sametozkan.kutuphane.entity.dto.request.TokenRefreshReq;
import com.sametozkan.kutuphane.entity.dto.response.TokenRefreshRes;
import com.sametozkan.kutuphane.entity.model.RefreshToken;
import com.sametozkan.kutuphane.entity.repository.AccountRepository;
import com.sametozkan.kutuphane.entity.repository.RefreshTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refreshtoken.validity}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccountRepository accountRepository;
    private final JwtUtils jwtUtils;

    public TokenRefreshRes refreshToken(TokenRefreshReq tokenRefreshReq) throws Exception {
        System.out.println("Refresh token");
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByToken(tokenRefreshReq.getRefreshToken());

        if (refreshTokenOptional.isPresent()) {
            System.out.println("Veritabanında bulundu!");
            RefreshToken refreshToken = refreshTokenOptional.get();
            verifyExpiration(refreshToken);
            return TokenRefreshRes.builder()
                    .jwt(jwtUtils.generateJwtToken(refreshToken.getAccount().getEmail()))
                    .refreshToken(refreshToken.getToken())
                    .refreshExpiryDate(refreshToken.getExpiryDate().toEpochMilli())
                    .build();

        } else {
            System.out.println("Veritabanında bulunamadı");
            throw new Exception("Refresh token veritabaninda bulunamadi.");
        }

    }

    public RefreshToken createRefreshToken(Long accountId) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .account(accountRepository.getReferenceById(accountId))
                .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
                .build();

        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) throws Exception {
        System.out.println("Verify");
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            System.out.println("Zaman geçmiş. Token silindi.");
            refreshTokenRepository.delete(token);
            throw new Exception("Refresh token suresi doldu. Lutfen tekrar giris yapin.");
        }
        System.out.println("Zaman geçmemiş. Token return edildi.");
        return token;
    }

    @Transactional
    public void deleteByAccountId(Long accountId) {
        refreshTokenRepository.deleteByAccountId(accountRepository.getReferenceById(accountId).getId());
    }

}
