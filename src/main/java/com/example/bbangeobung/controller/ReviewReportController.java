package com.example.bbangeobung.controller;


import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.ReviewReportListResponseDto;
import com.example.bbangeobung.dto.ReviewReportResponseDto;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.service.ReviewReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class ReviewReportController {
    private final ReviewReportService reviewReportService;

    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/report/review")
    public ResponseDto<ReviewReportListResponseDto> getReviewReports(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", reviewReportService.getReviewReports());
    }

    @PostMapping("/api/admin/report/review/{review_id}")
    public ResponseDto<ReviewReportResponseDto> createReviewReports(@PathVariable Long review_id, @RequestBody String reason, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseDto.of(HttpStatus.OK, "신고 완료", reviewReportService.createReviewReport(review_id, reason, userDetails.getUsername()));
    }
}
