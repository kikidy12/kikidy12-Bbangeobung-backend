package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.StoreInfoFishBreadType;
import com.example.bbangeobung.entity.StoreReport;
import com.example.bbangeobung.entity.User;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

public class StoreDto {

    @Getter
    public static class StoreAdd {
        private Double latitude;
        private Double longitude;
        private String imageURL;
        private String content;

        @ManyToOne(fetch = LAZY)
        private User user;
        @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<StoreInfoFishBreadType> infoFishBreadTypes;
        @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<StoreReport> reviewReports;
    }
}
