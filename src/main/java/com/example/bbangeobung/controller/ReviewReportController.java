package com.example.bbangeobung.controller;


import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.ReviewReportListResponseDto;
import com.example.bbangeobung.dto.ReviewReportResponseDto;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.ReviewReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ReviewReport")
@RestController
@RequiredArgsConstructor
public class ReviewReportController {
    private final ReviewReportService reviewReportService;

    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/report/review")
    @Operation(summary = "리뷰 신고 조회", description = "리뷰 신고 조회 관리자만 가능")
    public ResponseDto<ReviewReportListResponseDto> getReviewReports(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", reviewReportService.getReviewReports());
    }

    @PostMapping("/api/report/review/{review_id}")
    @Operation(summary = "리뷰 신고", description = "리뷰 신고")
    public ResponseDto<ReviewReportResponseDto> createReviewReports(
            @PathVariable Long review_id,
            @RequestBody String reason,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.of(HttpStatus.OK, "신고 완료", reviewReportService.createReviewReport(review_id, reason, userDetails.getUser()));
    }
}
