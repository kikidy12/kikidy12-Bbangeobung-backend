package com.example.bbangeobung.controller;


import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.ReviewReportListResponseDto;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.service.ReviewReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class ReviewReportController {
    private final ReviewReportService reviewReportService;

    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/report/review")
    public ResponseDto<ReviewReportListResponseDto> getReviewReports(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", reviewReportService.getReviewReports());
    }
}
