package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.ReviewRequestDto;
import com.example.bbangeobung.dto.ReviewResponseDto;
import com.example.bbangeobung.entity.Review;
import com.example.bbangeobung.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping("/{storeId}")
    public ReviewResponseDto createReview(@PathVariable Long storeId, @RequestBody ReviewRequestDto requestDto) {
        return reviewService.createReview(storeId, requestDto);
    }

    //리뷰 수정
    @PutMapping("/{storeId)/{reviewId)")
    public ResponseDto<ReviewResponseDto> updateReview(@PathVariable Long storeId, @PathVariable Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        return ResponseDto.of(HttpStatus.OK, "수정 성공", reviewService.updateReview(storeId, reviewId, requestDto));
    }

    //리뷰 삭제
    @DeleteMapping("/{storeId}/{reviewId}")
    public ResponseDto<Review> deleteReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ResponseDto.of(HttpStatus.OK, "삭제 성공", reviewService.deleteReview(storeId, reviewId));
    }
}
