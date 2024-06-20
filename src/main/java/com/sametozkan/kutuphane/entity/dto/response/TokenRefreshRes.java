package com.sametozkan.kutuphane.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TokenRefreshRes {

    private String jwt;
    private String refreshToken;
    private Long refreshExpiryDate;
}
