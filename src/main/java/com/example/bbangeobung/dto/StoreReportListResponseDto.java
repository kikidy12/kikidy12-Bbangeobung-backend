package com.example.bbangeobung.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class StoreReportListResponseDto {
    private List<StoreReportResponseDto> reports = new ArrayList<>();

    public void addReports(StoreReportResponseDto report) {
        reports.add(report);
    }
}
