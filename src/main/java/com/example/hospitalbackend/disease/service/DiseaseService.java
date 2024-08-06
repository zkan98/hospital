package com.example.hospitalbackend.disease.service;

import com.example.hospitalbackend.disease.entity.Disease;
import com.example.hospitalbackend.disease.repository.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    public Disease createDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }

    public Disease getDiseaseById(Long id) {
        return diseaseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Disease not found"));
    }

    public Disease updateDisease(Long id, Disease disease) {
        Disease existingDisease = diseaseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Disease not found"));
        existingDisease.setName(disease.getName());
        existingDisease.setSymptoms(disease.getSymptoms());
        existingDisease.setTreatment(disease.getTreatment());
        return diseaseRepository.save(existingDisease);
    }

    public void deleteDisease(Long id) {
        diseaseRepository.deleteById(id);
    }
}
