package com.example.bbangeobung.entity;

import com.example.bbangeobung.dto.StoreDto;
import com.example.bbangeobung.dto.StoreItemDto;
import com.example.bbangeobung.dto.V2StoreDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor
@Entity
public class Store extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StoreItem> storeItems;

    @OneToMany(mappedBy = "store", cascade =  CascadeType.ALL, orphanRemoval = true)
    private Set<StoreLikeUsers> storeLikeUsers;

    @Builder
    public Store(Double latitude, Double longitude, String imageURL, String content, User user, Set<StoreInfoFishBreadType> infoFishBreadTypes, Set<StoreItem> storeItems) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageURL = imageURL;
        this.content = content;
        this.user = user;
        this.infoFishBreadTypes = infoFishBreadTypes;
        this.storeItems = storeItems;
    }


    public void addInfoFishBreadTypes(StoreInfoFishBreadType storeInfoFishBreadType) {
        this.infoFishBreadTypes.add(storeInfoFishBreadType);
        if (!storeInfoFishBreadType.getStore().equals(this)) {
            storeInfoFishBreadType.setStore(this);
        }
    }

    public void addItem(StoreItem item) {
        this.storeItems.add(item);
        if (!item.getStore().equals(this)) {
            item.setStore(this);
        }
    }

    public List<StoreDto.ItemDto> makeItemMapDto() {
        if (this.infoFishBreadTypes == null)
            return new ArrayList<>();
        return this.infoFishBreadTypes.stream().map(v ->
                StoreDto.ItemDto
                        .builder()
                        .name(v.fishBreadType.getName())
                        .price(v.getPrice())
                        .build()).toList();
    }

    public List<StoreItemDto.StoreItemRes> makeStoreItemMapDto() {
        if (this.storeItems == null)
            return new ArrayList<>();
        return this.storeItems.stream().map(v ->
                StoreItemDto.StoreItemRes
                        .builder()
                        .name(v.getName())
                        .price(v.getPrice())
                        .build()).toList();
    }
}
