package com.example.bbangeobung.repository;

import com.example.bbangeobung.dto.CommentResponseDto;
import com.example.bbangeobung.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<CommentResponseDto> findAllByStoreId(Long storeId);
    List<CommentResponseDto> findAllByStoreId(Long storeId);
}