package com.example.bbangeobung.repository;

import com.example.bbangeobung.entity.Comment;
import com.example.bbangeobung.entity.CommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReportRepository extends JpaRepository<CommentReport,Long> {

}
