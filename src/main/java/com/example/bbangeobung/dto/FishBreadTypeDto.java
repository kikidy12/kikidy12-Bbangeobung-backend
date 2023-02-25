package com.example.bbangeobung.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FishBreadTypeDto {

    @Getter
    @NoArgsConstructor
    public static class FishBreadTypeAdd {
        private String name;

        @Builder
        public FishBreadTypeAdd(String name) {
            this.name = name;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class FishBreadTypeUpdate {
        private String name;

        @Builder
        public FishBreadTypeUpdate(String name) {
            this.name = name;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class FishBreadTypeRes {

        private Long id;
        private String name;

        @Builder
        public FishBreadTypeRes(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
