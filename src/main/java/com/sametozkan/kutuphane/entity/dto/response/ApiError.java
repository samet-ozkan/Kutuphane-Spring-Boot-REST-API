package com.sametozkan.kutuphane.entity.dto.response;

import lombok.Data;

@Data
public class ApiError {
    private int statusCode;
    private String errorCode;
    private String message;

    public ApiError(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

}
