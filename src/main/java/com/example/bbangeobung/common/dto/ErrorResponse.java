package com.example.bbangeobung.common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

    private Integer code;

    private String message;

    public ErrorResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}