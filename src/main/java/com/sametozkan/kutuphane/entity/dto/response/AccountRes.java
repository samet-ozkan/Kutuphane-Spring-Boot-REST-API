package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRes {

    private Long id;
    private String email;
    private String type;

}
