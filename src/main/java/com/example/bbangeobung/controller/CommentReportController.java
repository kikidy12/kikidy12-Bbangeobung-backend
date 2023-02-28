package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.*;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.CommentReportService;
import com.example.bbangeobung.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report/comment")
@RequiredArgsConstructor
public class CommentReportController {
    private final CommentReportService commentReportService;
    private final CommentService commentService;

    //댓글 신고
    @PostMapping("/{commentId}")
    @Operation(summary = "댓글 신고", description = "댓글 신고")
    public ResponseDto<CommentReportResponseDto> createCommentReport(@RequestBody CommentReportRequestDto requestDto,
                                                              @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentReportService.createCommentReport(requestDto,userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "댓글 신고 완료");

    }

    //신고받은 댓글 리스트 조회
    @GetMapping("/")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @Operation(summary = "신고받은 댓글 리스트 조회", description = "신고받은 댓글 리스트 조회는 관리자만 가능")
    public ResponseDto<CommentReportListResponseDto> getCommentReports(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseDto.of(HttpStatus.OK,"신고받은 댓글 전체 조회 성공", commentReportService.getCommentReports());

    }


    // 신고 받은 댓글 삭제
    @DeleteMapping("/{commentId}")
    @Operation(summary = "신고 받은 댓글 삭제", description = "신고 받은 댓글 삭제 관리자만 삭제 가능")
    public ResponseDto<CommentReportResponseDto> deleteCommentReport(@PathVariable Long commentId,
                                                                     @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){

        commentReportService.deleteCommentReport(commentId,userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK,"신고 받은 댓글 삭제 성공");
    }

}
