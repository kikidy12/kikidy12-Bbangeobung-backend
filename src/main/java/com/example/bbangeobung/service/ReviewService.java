package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.dto.ReviewRequestDto;
import com.example.bbangeobung.dto.ReviewResponseDto;
import com.example.bbangeobung.entity.Review;
import com.example.bbangeobung.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

////    리뷰 조회
//    @Transactional(readOnly = true)
//    public Review getReview(Long storeId) {
//        Review review = reviewRepository.findById(storeId).orElseThrow();
//        return review;
//    }

    //리뷰 작성
    @Transactional
    public ReviewResponseDto createReview(Long storeId, ReviewRequestDto requestDto) {
//      유저 인증 구현
        Review review = reviewRepository.save(new Review(requestDto));

        return new ReviewResponseDto(review);
    }

    //리뷰 수정
    @Transactional
    public ReviewResponseDto updateReview(Long storeID, Long reviewId, ReviewRequestDto requestDto) {
        //storeId find
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new CustomClientException("리뷰가 존재하지 않습니다.")
        );

        //유저 인증

        review.updateReview(requestDto);
        return new ReviewResponseDto(review);
    }

    //리뷰 삭제
    @Transactional
    public Review deleteReview(Long reviewId, Long storeId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new CustomClientException("존재하지 않는 리뷰입니다.")
        );

//        유저 인증

        reviewRepository.deleteById(reviewId);
        return review;
    }
}
