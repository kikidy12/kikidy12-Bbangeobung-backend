package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.ReviewRequestDto;
import com.example.bbangeobung.dto.ReviewResponseDto;
import com.example.bbangeobung.entity.Review;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.ReviewService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //리뷰 조회
    @GetMapping("/")
    public ResponseDto<List<ReviewResponseDto>> reviewList(@RequestParam Long storeId) {
        return ResponseDto.of(HttpStatus.OK, "리뷰 조회 성공", reviewService.reviewList(storeId));
    }

    @GetMapping("/{reviewId}")
    public ResponseDto<ReviewResponseDto> getReview(@PathVariable Long reviewId) {
        return ResponseDto.of(HttpStatus.OK, "리뷰 조회 성공", reviewService.getReview(reviewId));
    }

    //리뷰 작성
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ReviewResponseDto> createReview(
                                                      @ModelAttribute ReviewRequestDto requestDto,
                                                      @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return ResponseDto.of(HttpStatus.OK, "리뷰 등록 성공", reviewService.createReview(requestDto, userDetails.getUser()));
    }

    //리뷰 수정
    @PutMapping(value= "/{reviewId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ReviewResponseDto> updateReview(@PathVariable Long reviewId,
                                                       @ModelAttribute ReviewRequestDto requestDto,
                                                       @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return ResponseDto.of(HttpStatus.OK, "리뷰 수정 성공", reviewService.updateReview(reviewId, requestDto, userDetails.getUser()));
    }

    //리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseDto deleteReview(@PathVariable Long reviewId,
                                                       @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.deleteReview(reviewId, userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "리뷰 삭제 성공");
    }
}
