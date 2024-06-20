package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class JwtRes {

    private String jwt;
    private Long accountId;
    private String accountType;
    private String email;
    private String refreshToken;
    private Long refreshExpiryDate;
}
