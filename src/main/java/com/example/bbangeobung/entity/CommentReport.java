package com.example.bbangeobung.entity;
// 댓글 신고 기능 구현하기
import com.example.bbangeobung.dto.CommentReportRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CommentReport extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason; // 신고 원인
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public CommentReport(CommentReportRequestDto requestDto, Comment comment, User user) {
        this.reason = requestDto.getReason();
        this.comment = comment;
        this.user = user;
    }
}
