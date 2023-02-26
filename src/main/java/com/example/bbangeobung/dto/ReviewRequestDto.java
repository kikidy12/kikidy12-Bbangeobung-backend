package com.example.bbangeobung.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
//@NoArgsConstructor
public class ReviewRequestDto {
    private String message;
    private MultipartFile imageURL;

    private Long storeID;

    @Builder
    public ReviewRequestDto(String message, MultipartFile imageURL, Long storeID) {
        this.message = message;
        this.imageURL = imageURL;
        this.storeID = storeID;
    }
}
