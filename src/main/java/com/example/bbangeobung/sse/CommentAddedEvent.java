package com.example.bbangeobung.sse;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CommentAddedEvent extends ApplicationEvent {
    private final long storeId;
    private final long commentId;

    public CommentAddedEvent(Object source, long storeId, long commentId) {
        super(source);
        this.storeId = storeId;
        this.commentId = commentId;
    }
}