package com.example.bbangeobung.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentReportRequestDto {
    private String reason;
    public Long commentId;

//    public String username;

    public CommentReportRequestDto(String reason, Long commentId) {
        this.reason = reason;
        this.commentId = commentId;
//        this.username = username;
    }
}
