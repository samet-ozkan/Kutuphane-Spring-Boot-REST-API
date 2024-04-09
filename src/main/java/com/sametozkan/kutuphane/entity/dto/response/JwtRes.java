package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRes {

    private String jwt;
}
