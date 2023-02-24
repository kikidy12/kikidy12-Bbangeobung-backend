package com.example.bbangeobung.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class StoreLikeUsers extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    @ManyToOne(fetch = LAZY)
    User user;

    @ManyToOne(fetch = LAZY)
    Store store;
}
