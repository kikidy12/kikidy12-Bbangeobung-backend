package com.example.bbangeobung.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor
@Entity
public class StoreInfoFishBreadType extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    @ManyToOne(fetch = LAZY)
    FishBreadType fishBreadType;

    @ManyToOne(fetch = LAZY)
    Store store;

    public void setStore(Store store) {
        if(this.store != null) {
            this.store.getInfoFishBreadTypes().remove(this);
        }
        this.store = store;
        if(!store.getInfoFishBreadTypes().contains(this)) {
            store.addInfoFishBreadTypes(this);
        }
    }

    @Builder
    public StoreInfoFishBreadType(Integer price, FishBreadType fishBreadType) {
        this.price = price;
        this.fishBreadType = fishBreadType;
    }
}
