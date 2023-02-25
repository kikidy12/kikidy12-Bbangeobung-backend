package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.ReviewReportListResponseDto;
import com.example.bbangeobung.dto.ReviewReportResponseDto;
import com.example.bbangeobung.dto.StoreReportListResponseDto;
import com.example.bbangeobung.dto.StoreReportResponseDto;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.StoreReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "StoreReport")
@RestController
@RequiredArgsConstructor
public class StoreReportController {
    private final StoreReportService storeReportService;

    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/report/store")
    @Operation(summary = "허위 상점 신고 조회", description = "허위 상점 신고 조회 관리자만 가능")
    public ResponseDto<StoreReportListResponseDto> getStoreReports(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", storeReportService.getStoreReports());
    }

    @PostMapping("/api/report/store/{store_id}")
    @Operation(summary = "허위 상점 신고", description = "허위 상점 신고")
    public ResponseDto<StoreReportResponseDto> createStoreReports(
            @PathVariable Long store_id,
            @RequestBody String reason,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.of(HttpStatus.OK, "신고 완료", storeReportService.createStoreReport(store_id, reason, userDetails.getUser()));
    }
}
