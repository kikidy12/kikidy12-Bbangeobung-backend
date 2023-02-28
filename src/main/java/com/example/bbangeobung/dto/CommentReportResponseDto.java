package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.CommentReport;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentReportResponseDto {
    private Long id;
    private Long commentId;
    private String username;
    private String reason;
    private LocalDateTime createdAt;

    public CommentReportResponseDto(CommentReport report) {
        this.id = report.getId();
        this.commentId = report.getComment().getId();
        this.username = report.getUser().getUsername();
        this.reason = report.getReason();
        this.createdAt = report.getCreatedAt();
    }
}
