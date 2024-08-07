package com.example.hospitalbackend.recommendation.repository;

import com.example.hospitalbackend.recommendation.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findByDiseaseAndLatitudeBetweenAndLongitudeBetween(
        String disease, double latStart, double latEnd, double lonStart, double lonEnd);
}
