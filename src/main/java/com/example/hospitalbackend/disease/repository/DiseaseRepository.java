package com.example.hospitalbackend.disease.repository;

import com.example.hospitalbackend.disease.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
