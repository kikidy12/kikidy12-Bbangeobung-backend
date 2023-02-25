package com.example.bbangeobung.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class FishBreadType extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "fishBreadType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StoreInfoFishBreadType> infoFishBreadTypes;

    @Builder
    public FishBreadType(String name, Set<StoreInfoFishBreadType> infoFishBreadTypes) {
        this.name = name;
        this.infoFishBreadTypes = infoFishBreadTypes;
    }

    public void update(String name) {
        this.name = name;
    }
}
