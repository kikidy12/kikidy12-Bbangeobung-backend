package com.example.bbangeobung.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@Builder
@AllArgsConstructor
public class ResponseDto<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResponseDto of(final HttpStatus code, final String message) {
        return ResponseDto.builder()
                .code(code.value())
                .message(message)
                .build();
    }

    public static <T> ResponseDto<T> of(final HttpStatus code, final String message, final T data) {
        return ResponseDto.<T>builder()
                .data(data)
                .code(code.value())
                .message(message)
                .build();
    }
}

