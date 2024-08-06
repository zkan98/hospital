package com.example.hospitalbackend.recommendation.controller;

import com.example.hospitalbackend.recommendation.entity.Recommendation;
import com.example.hospitalbackend.recommendation.service.RecommendationService;
import com.example.hospitalbackend.hospital.entity.Hospital;
import com.example.hospitalbackend.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<List<Recommendation>> getRecommendations(@RequestParam String disease,
        @RequestParam double latitude,
        @RequestParam double longitude) {
        return ResponseEntity.ok(recommendationService.getRecommendations(disease, latitude, longitude));
    }

    @PostMapping
    public ResponseEntity<Recommendation> addRecommendation(@RequestParam Long hospitalId,
        @RequestParam double score,
        @RequestParam String disease) {
        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        Recommendation recommendation = recommendationService.addRecommendation(hospital, score, disease);
        return ResponseEntity.ok(recommendation);
    }
}
