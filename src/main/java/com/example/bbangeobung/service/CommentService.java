package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.dto.CommentRequestDto;
import com.example.bbangeobung.dto.CommentResponseDto;
import com.example.bbangeobung.entity.Comment;
import com.example.bbangeobung.entity.Store;
import com.example.bbangeobung.entity.User;
import com.example.bbangeobung.repository.CommentRepository;
import com.example.bbangeobung.repository.StoreRepository;
import com.example.bbangeobung.sse.CommentAddedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final StoreRepository storeRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    //댓글 등록
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Store store = storeRepository.findById(requestDto.getStoreId()).orElseThrow(
                () -> new CustomClientException("가게가 존재하지 않습니다.")
        );

        Comment comment = commentRepository.save(new Comment(requestDto, store, user));

        return new CommentResponseDto(comment);
    }

    public void onCommentAdded(long storeId, long commentId) {
        CommentAddedEvent event = new CommentAddedEvent(this, storeId, commentId);
        applicationEventPublisher.publishEvent(event);
    }

    //댓글 조회
    @Transactional
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomClientException("댓글이 존재하지 않습니다.")
        );
        return new CommentResponseDto(comment);
    }

    //댓글 리스트
    @Transactional
    public List<CommentResponseDto> getCommentList(Long storeId) {
        List<CommentResponseDto> commentList = commentRepository.findAllByStoreId(storeId);
        return commentList;
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomClientException("존재하지 않는 댓글입니다.")
        );

        if(!user.getId().equals(comment.getUser().getId())) {
            throw new CustomClientException("ID가 일치하지 않습니다.");
        }
        comment.updateComment(commentId, requestDto.getComment());
        return new CommentResponseDto(comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomClientException("존재하지 않는 댓글입니다.")
        );

        if(!user.getId().equals(comment.getUser().getId())) {
            throw new CustomClientException("ID가 일치하지 않습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
