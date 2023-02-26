package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.dto.ReviewRequestDto;
import com.example.bbangeobung.dto.ReviewResponseDto;
import com.example.bbangeobung.entity.Review;
import com.example.bbangeobung.entity.Store;
import com.example.bbangeobung.entity.User;
import com.example.bbangeobung.repository.ReviewRepository;
import com.example.bbangeobung.repository.StoreRepository;
import com.example.bbangeobung.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final S3Uploader s3Uploader;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    //리뷰 조회
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> reviewList(Long storeId) {
        List<ReviewResponseDto> reviewList = reviewRepository.findAllByStoreId(storeId);
        return reviewList;
    }

    //리뷰 작성
    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto requestDto, User user) {
        try {
            Store store = storeRepository.findById(requestDto.getStoreID()).orElseThrow(
                    () -> new CustomClientException("가게가 존재하지 않습니다.")
            );

            String imageURL = s3Uploader.upload(requestDto.getImageURL());
            Review review = Review.builder().
                    store(store).
                    user(user).
                    message(requestDto.getMessage()).
                    imageURL(imageURL).
                    build();
            review = reviewRepository.save(review);
            return new ReviewResponseDto(review);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //리뷰 수정
    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto requestDto, User user) {
        try {
            Review review = reviewRepository.findById(reviewId).orElseThrow(
                    () -> new CustomClientException("리뷰가 존재하지 않습니다.")
            );

            if (!user.getId().equals(review.getUser().getId())) {
                throw new CustomClientException("사용자가 일치하지 않습니다.");
            } else {
                String imageURL = s3Uploader.upload(requestDto.getImageURL());
                review.updateReview(reviewId, requestDto.getMessage(), imageURL);
            }

            return new ReviewResponseDto(review);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //리뷰 삭제
    @Transactional
    public void deleteReview(Long reviewId, User user) {
        System.out.println(reviewId);

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new CustomClientException("존재하지 않는 리뷰입니다.")
        );

        if (!user.getId().equals(review.getUser().getId())) {
            throw new CustomClientException("사용자가 일치하지 않습니다.");
        } else {
            reviewRepository.deleteById(reviewId);
        }
    }

    @Transactional
    public ReviewResponseDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new CustomClientException("리뷰가 존재하지 않습니다.")
        );

        return new ReviewResponseDto(review);
    }
}
