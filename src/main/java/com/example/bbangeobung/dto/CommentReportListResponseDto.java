package com.example.bbangeobung.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class CommentReportListResponseDto {
    private List<CommentReportResponseDto> reports = new ArrayList<>();

    public void addReports(CommentReportResponseDto commentReportResponseDto) {
        reports.add(commentReportResponseDto);
    }
}

