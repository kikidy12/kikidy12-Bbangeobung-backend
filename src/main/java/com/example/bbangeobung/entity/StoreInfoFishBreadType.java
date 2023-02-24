package com.example.bbangeobung.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
