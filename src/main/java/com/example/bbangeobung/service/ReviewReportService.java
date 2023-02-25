package com.example.bbangeobung.service;


import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.dto.ReviewReportListResponseDto;
import com.example.bbangeobung.dto.ReviewReportResponseDto;
import com.example.bbangeobung.entity.Review;
import com.example.bbangeobung.entity.ReviewReport;
import com.example.bbangeobung.entity.User;
import com.example.bbangeobung.repository.ReviewReportRepository;
import com.example.bbangeobung.repository.ReviewRepository;
import com.example.bbangeobung.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewReportService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewReportRepository reviewReportRepository;

    @Transactional(readOnly = true)
    public ReviewReportListResponseDto getReviewReports() {

        ReviewReportListResponseDto responseDto = new ReviewReportListResponseDto();

        List<ReviewReport> reports = reviewReportRepository.findAll();

        for(ReviewReport report : reports) {
            responseDto.addReports(new ReviewReportResponseDto(report));
        }

        return responseDto;
    }


    @Transactional
    public ReviewReportResponseDto createReviewReport(Long reviewId,String reason, User user) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new CustomClientException("리뷰가 존재하지 않습니다.")
        );

        ReviewReport report = new ReviewReport(reason, user, review);
        reviewReportRepository.save(report);

        return new ReviewReportResponseDto(report);

    }
}
