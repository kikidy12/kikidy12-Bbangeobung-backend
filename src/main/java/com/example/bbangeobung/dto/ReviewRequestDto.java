package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.Review;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private String message;
    private String imageURL;
}
