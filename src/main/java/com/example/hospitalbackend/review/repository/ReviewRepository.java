package com.example.hospitalbackend.review.repository;

import com.example.hospitalbackend.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
