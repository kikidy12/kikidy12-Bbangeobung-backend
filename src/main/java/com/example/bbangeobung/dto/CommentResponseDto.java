package com.example.bbangeobung.dto;

import com.example.bbangeobung.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String username;

    private String comment;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUser().getUsername();
        this.comment = comment.getComment();
    }
}
