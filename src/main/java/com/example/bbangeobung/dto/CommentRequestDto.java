package com.example.bbangeobung.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String comment;
    private Long storeId;

    public CommentRequestDto(String comment, Long storeId) {
        this.comment = comment;
        this.storeId = storeId;
    }
}
