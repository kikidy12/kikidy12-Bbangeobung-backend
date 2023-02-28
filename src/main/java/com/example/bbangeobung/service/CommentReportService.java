package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.controller.CommentReportController;
import com.example.bbangeobung.dto.CommentReportListResponseDto;
import com.example.bbangeobung.dto.CommentReportRequestDto;
import com.example.bbangeobung.dto.CommentReportResponseDto;
import com.example.bbangeobung.entity.Comment;
import com.example.bbangeobung.entity.CommentReport;
import com.example.bbangeobung.entity.User;
import com.example.bbangeobung.entity.UserRoleEnum;
import com.example.bbangeobung.repository.CommentReportRepository;
import com.example.bbangeobung.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentReportService {
    private final CommentRepository commentRepository;
    private final CommentReportRepository commentReportRepository;


    // 댓글 신고 기능
    @Transactional
    public CommentReportResponseDto createCommentReport(CommentReportRequestDto requestDto, User user) {
        //댓글 존재여부 확인
        Comment comment = commentRepository.findById(requestDto.getCommentId()).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        // 본인 댓글 신고하기 방지
        if (user.getId()==comment.getUser().getId()){
            throw new IllegalArgumentException("본인이 작성한 댓글은 신고할 수 없습니다.");
        }

        CommentReport report = new CommentReport(requestDto, comment,user);
        commentReportRepository.save(report);

        return new CommentReportResponseDto(report);
    }


     // 신고 받은 댓글 전체 조회
    @Transactional(readOnly = true)
    public CommentReportListResponseDto getCommentReports() {
        CommentReportListResponseDto responseDto = new CommentReportListResponseDto();
        List<CommentReport> reports = commentReportRepository.findAll();

        for(CommentReport report:reports){
            responseDto.addReports(new CommentReportResponseDto(report));
        }
        return responseDto;
    }

    @Transactional
    public void  deleteCommentReport(Long commentId, User user) {
        //댓글이 존재하는지 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );

        // 관리자만 삭제가능
        if (user.getRole().equals(UserRoleEnum.ADMIN)) {
            commentRepository.deleteById(comment.getId());
            return;
        }
        throw new IllegalArgumentException("삭제할 권한이 없습니다.");
    }

}
