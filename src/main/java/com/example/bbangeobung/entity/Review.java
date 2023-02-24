package com.example.bbangeobung.entity;

import com.example.bbangeobung.dto.ReviewRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor
@Entity
public class Review extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String imageURL;

    @ManyToOne(fetch = LAZY)
    private User user;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewReport> reviewReports;

    //    public Review(ReviewRequestDto requestDto, User user) {
//        this.user = user;
//        this.message = requestDto.getMessage();
//        this.imageURL = requestDto.getImageURL();
//    }
    public Review(ReviewRequestDto requestDto) {
        this.user = user;
        this.message = requestDto.getMessage();
        this.imageURL = requestDto.getImageURL();
    }

    public void updateReview(ReviewRequestDto requestDto) {
        this.message = requestDto.getMessage();
        this.imageURL = requestDto.getImageURL();
    }
}
