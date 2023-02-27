package com.example.bbangeobung.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class StoreItemDto {

    @Getter
    @NoArgsConstructor
    public static class StoreItemAdd {

        private String name;
        private Integer price;

        @Builder
        public StoreItemAdd(String name, Integer price) {
            this.name = name;
            this.price = price;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class StoreItemRes {

        private String name;

        private Integer price;


        @Builder
        public StoreItemRes(String name, Integer price) {
            this.name = name;
            this.price = price;
        }
    }
}
