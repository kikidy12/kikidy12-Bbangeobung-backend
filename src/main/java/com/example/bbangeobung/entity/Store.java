package com.example.bbangeobung.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
