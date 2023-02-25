package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.StoreReport;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class StoreReportResponseDto {

    private Long storeId;
    private String username;
    private String reason;
    private LocalDateTime createdAt;

    public StoreReportResponseDto(StoreReport report) {
        this.storeId = report.getStore().getId();
        this.username = report.getUser().getUsername();
        this.reason = report.getReason();
        this.createdAt = report.getCreatedAt();
    }

}
