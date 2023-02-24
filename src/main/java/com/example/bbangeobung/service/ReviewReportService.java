package com.example.bbangeobung.service;


import com.example.bbangeobung.dto.ReviewReportListResponseDto;
import com.example.bbangeobung.dto.ReviewReportResponseDto;
import com.example.bbangeobung.entity.ReviewReport;
import com.example.bbangeobung.repository.ReviewReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewReportService {

    private final ReviewReportRepository reviewReportRepository;

    public ReviewReportListResponseDto getReviewReports() {

        ReviewReportListResponseDto responseDto = new ReviewReportListResponseDto();

        List<ReviewReport> reports = reviewReportRepository.findAll();

        for(ReviewReport report : reports) {
            responseDto.addReports(new ReviewReportResponseDto(report));
        }

        return responseDto;
    }
}
