package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.ReviewReportListResponseDto;
import com.example.bbangeobung.dto.ReviewReportResponseDto;
import com.example.bbangeobung.dto.StoreReportListResponseDto;
import com.example.bbangeobung.dto.StoreReportResponseDto;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.StoreReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreReportController {
    private final StoreReportService storeReportService;

    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/report/store")
    public ResponseDto<StoreReportListResponseDto> getStoreReports(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", storeReportService.getStoreReports());
    }

    @PostMapping("/api/report/store/{store_id}")
    public ResponseDto<StoreReportResponseDto> createStoreReports(@PathVariable Long store_id, @RequestBody String reason, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.of(HttpStatus.OK, "신고 완료", storeReportService.createStoreReport(store_id, reason, userDetails.getUser()));
    }
}
