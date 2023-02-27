package com.example.bbangeobung.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class StoreItem extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer price;

    @ManyToOne(fetch = LAZY)
    Store store;

    public void setStore(Store store) {
        if(this.store != null) {
            this.store.getInfoFishBreadTypes().remove(this);
        }
        this.store = store;
        if(!store.getInfoFishBreadTypes().contains(this)) {
            store.addItem(this);
        }
    }
}
