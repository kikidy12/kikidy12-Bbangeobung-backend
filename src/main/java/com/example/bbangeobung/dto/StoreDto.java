package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.StoreInfoFishBreadType;
import com.example.bbangeobung.entity.StoreReport;
import com.example.bbangeobung.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
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
        private Double latitude;
        private Double longitude;
        private String content;

        private MultipartFile imageFile;

        List<String> itemList;

        @Builder
        public StoreAdd(Double latitude, Double longitude, String content, MultipartFile imageFile, List<String> itemList) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.content = content;
            this.imageFile = imageFile;
            this.itemList = itemList;
        }
    }

    @Getter
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
    public static class ItemDto {
        private String name;
        private Integer price;

        @Builder
        public ItemDto(String name, Integer price) {
            this.name = name;
            this.price = price;
        }
    }
}
