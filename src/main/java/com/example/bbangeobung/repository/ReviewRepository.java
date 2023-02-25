package com.example.bbangeobung.repository;

import com.example.bbangeobung.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
