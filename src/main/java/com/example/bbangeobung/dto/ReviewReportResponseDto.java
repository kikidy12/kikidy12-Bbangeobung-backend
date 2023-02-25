package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.ReviewReport;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReviewReportResponseDto {

    private Long reviewId;
    private String username;
    private String reason;
    private LocalDateTime createdAt;

    public ReviewReportResponseDto(ReviewReport report) {
        this.reviewId = report.getReview().getId();
        this.username = report.getUser().getUsername();
        this.reason = report.getReason();
        this.createdAt = report.getCreatedAt();
    }
}
