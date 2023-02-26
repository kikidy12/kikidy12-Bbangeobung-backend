package com.example.bbangeobung.repository;

import com.example.bbangeobung.dto.ReviewResponseDto;
import com.example.bbangeobung.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<ReviewResponseDto> findAllByStoreId(Long storeId);
}
