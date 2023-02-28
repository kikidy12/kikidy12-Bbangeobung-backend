package com.example.bbangeobung.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class V2StoreDto {

    @Getter
    public static class V2StoreAdd {
        @Schema(defaultValue = "37.4898", description = "위도")
        private Double latitude;
        @Schema(defaultValue = "126.8369", description = "경도")
        private Double longitude;
        @Schema(defaultValue = "붕어빵천국", description = "매점에 대한 설명")
        private String content;

        @Schema(description = "매점 사진")
        private MultipartFile imageFile;

        @Schema(
                defaultValue = "[{\"name\": \"팥붕어빵\", \"price\": 1000}]",
                description = "name: 붕어빵 이름\nprice:붕어빵 가격"
        )
        private String jsonList;
        @Builder
        public V2StoreAdd(Double latitude, Double longitude, String content, MultipartFile imageFile, String jsonList) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.content = content;
            this.imageFile = imageFile;
            this.jsonList = jsonList;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class V2StoreRes {

        private Long id;
        private Double latitude;
        private Double longitude;
        private String imageURL;
        private String content;

        private Integer likeCount;

        private Boolean isMyLike;
        private List<StoreItemDto.StoreItemRes> itemList;


        @Builder
        public V2StoreRes(Long id, Double latitude, Double longitude,
                          String imageURL, String content, List<StoreItemDto.StoreItemRes> itemList,
                          Integer likeCount, Boolean isMyLike
        ) {
            this.id = id;
            this.latitude = latitude;
            this.longitude = longitude;
            this.imageURL = imageURL;
            this.content = content;
            this.itemList = itemList;
            this.likeCount = likeCount;
            this.isMyLike = isMyLike;
        }
    }
}
