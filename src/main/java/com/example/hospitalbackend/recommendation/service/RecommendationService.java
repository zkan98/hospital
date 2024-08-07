package com.example.hospitalbackend.recommendation.service;

import com.example.hospitalbackend.recommendation.entity.Recommendation;
import com.example.hospitalbackend.recommendation.repository.RecommendationRepository;
import com.example.hospitalbackend.hospital.entity.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public List<Recommendation> getRecommendations(String disease, double latitude, double longitude) {
        double range = 0.05; // 5km 반경 예시
        double minLat = latitude - range;
        double maxLat = latitude + range;
        double minLon = longitude - range;
        double maxLon = longitude + range;

        return recommendationRepository.findByDiseaseAndLatitudeBetweenAndLongitudeBetween(
            disease, minLat, maxLat, minLon, maxLon);
    }

    public Recommendation addRecommendation(Hospital hospital, double score, String disease) {
        Recommendation recommendation = new Recommendation();
        recommendation.setHospital(hospital);
        recommendation.setScore(score);
        recommendation.setDisease(disease);
        recommendation.setLatitude(hospital.getLatitude());
        recommendation.setLongitude(hospital.getLongitude());
        return recommendationRepository.save(recommendation);
    }
}
