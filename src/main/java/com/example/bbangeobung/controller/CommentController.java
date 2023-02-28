package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.CommentRequestDto;
import com.example.bbangeobung.dto.CommentResponseDto;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comment")
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/")
    @Operation(summary = "댓글 등록", description = "댓글 등록")
    public ResponseDto<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto,
                                                         @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CommentResponseDto dto = commentService.createComment(requestDto, userDetails.getUser());

        commentService.onCommentAdded(requestDto.getStoreId(), dto.getId());
        return ResponseDto.of(HttpStatus.OK, "댓글 등록 성공", dto);
    }

    @GetMapping("/{commentId}")
    @SecurityRequirements()
    @Operation(summary = "댓글 조회", description = "댓글 조회")
    public ResponseDto<CommentResponseDto> getComment(@PathVariable Long commentId) {
        return ResponseDto.of(HttpStatus.OK, "댓글 조회 성공", commentService.getComment(commentId));
    }

    @GetMapping("/")
    @SecurityRequirements()
    @Operation(summary = "댓글 리스트 조회", description = "댓글 리스트 조회")
    public ResponseDto<List<CommentResponseDto>> getCommentList(@RequestParam Long storeId) {
        return ResponseDto.of(HttpStatus.OK, "댓글 리스트 조회 성공", commentService.getCommentList(storeId));
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정", description = "댓글 수정")
    public ResponseDto<CommentResponseDto> updateComment(@PathVariable Long commentId,
                                                         @RequestBody CommentRequestDto requestDto,
                                                         @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.of(HttpStatus.OK, "댓글 수정 성공", commentService.updateComment(commentId, requestDto, userDetails.getUser()));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제", description = "댓글 삭제")
    public ResponseDto<CommentResponseDto> deleteComment(@PathVariable Long commentId,
                                                         @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "댓글 삭제 성공");
    }
}