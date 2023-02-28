package com.example.bbangeobung.entity;

import com.example.bbangeobung.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = LAZY)
    private User user;

    @ManyToOne(fetch = LAZY)
    private Store store;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<CommentReport> commentReportSet;




    public Comment(CommentRequestDto requestDto, Store store, User user) {
        this.comment = requestDto.getComment();
        this.store = store;
        this.user = user;
    }

    public void updateComment(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }
}
