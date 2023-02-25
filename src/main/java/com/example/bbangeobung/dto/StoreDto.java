package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.StoreInfoFishBreadType;
import com.example.bbangeobung.entity.StoreReport;
import com.example.bbangeobung.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

public class StoreDto {

    @Getter
    public static class StoreAdd {
        @Schema(defaultValue = "37.4898", description = "위도")
        private Double latitude;
        @Schema(defaultValue = "126.8369", description = "경도")
        private Double longitude;
        @Schema(defaultValue = "붕어빵천국", description = "매점에 대한 설명")
        private String content;

        @Schema(description = "매점 사진")
        private MultipartFile imageFile;

        @Schema(
                defaultValue = "[{\"fishBreadTypeId\": 1, \"price\": 1000}]",
                description = "fishBreadTypeId: 붕어빵종류의 아이디\nprice:붕어빵 가격"
        )
        private String jsonList;
        @Builder
        public StoreAdd(Double latitude, Double longitude, String content, MultipartFile imageFile, String jsonList) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.content = content;
            this.imageFile = imageFile;
            this.jsonList = jsonList;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class StoreRes {

        private Long id;
        private Double latitude;
        private Double longitude;
        private String imageURL;
        private String content;
        private List<ItemDto> itemList;


        @Builder
        public StoreRes(Long id, Double latitude, Double longitude, String imageURL, String content, List<ItemDto> itemList) {
            this.id = id;
            this.latitude = latitude;
            this.longitude = longitude;
            this.imageURL = imageURL;
            this.content = content;
            this.itemList = itemList;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ItemDto {
        private String name;
        private Integer price;

        @Builder
        public ItemDto(String name, Integer price) {
            this.name = name;
            this.price = price;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class ItemAddDto {
        private Long fishBreadTypeId;

        private Integer price;

        @Builder
        public ItemAddDto(Long fishBreadTypeId, Integer price) {
            this.fishBreadTypeId = fishBreadTypeId;
            this.price = price;
        }
    }
}
