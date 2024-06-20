package com.sametozkan.kutuphane.entity.dto.request;

import lombok.Data;

@Data
public class TokenRefreshReq {
    private String refreshToken;
}
