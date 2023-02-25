package com.example.bbangeobung.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReviewReportListResponseDto {

    private List<ReviewReportResponseDto> reports = new ArrayList<>();

    public void addReports(ReviewReportResponseDto report) {
        reports.add(report);
    }
}
