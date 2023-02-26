package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.Review;
import com.example.bbangeobung.entity.ReviewReport;
import com.example.bbangeobung.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;

    private String message;

    private String imageURL;

    private String username;

    private Set<ReviewReport> reviewReports;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.message = review.getMessage();
        this.imageURL = review.getImageURL();
        this.username = review.getUser().getUsername();
    }

//    @Builder
//    public ReviewResponseDto(Long id, String message, String imageURL, String username) {
//        this.id = id;
//        this.message = message;
//        this.imageURL = imageURL;
//        this.username = username;
//    }
}
