package com.example.hospitalbackend.disease.controller;

import com.example.hospitalbackend.disease.entity.Disease;
import com.example.hospitalbackend.disease.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diseases")
@RequiredArgsConstructor
public class DiseaseController {

    private final DiseaseService diseaseService;

    @PostMapping
    public ResponseEntity<Disease> createDisease(@RequestBody Disease disease) {
        Disease createdDisease = diseaseService.createDisease(disease);
        return ResponseEntity.ok(createdDisease);
    }

    @GetMapping
    public ResponseEntity<List<Disease>> getAllDiseases() {
        List<Disease> diseases = diseaseService.getAllDiseases();
        return ResponseEntity.ok(diseases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disease> getDiseaseById(@PathVariable Long id) {
        Disease disease = diseaseService.getDiseaseById(id);
        return ResponseEntity.ok(disease);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disease> updateDisease(@PathVariable Long id, @RequestBody Disease disease) {
        Disease updatedDisease = diseaseService.updateDisease(id, disease);
        return ResponseEntity.ok(updatedDisease);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisease(@PathVariable Long id) {
        diseaseService.deleteDisease(id);
        return ResponseEntity.noContent().build();
    }
}
