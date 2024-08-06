package com.example.hospitalbackend.hospital.service;

import com.example.hospitalbackend.hospital.entity.Hospital;
import com.example.hospitalbackend.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {


    private final HospitalRepository hospitalRepository;

    // 병원 추천 메서드 추가
    public List<Hospital> recommendHospitals(double latitude, double longitude, String disease) {
        // 여기에 병원을 추천하는 로직을 추가합니다.
        double latMin = latitude - 0.1;
        double latMax = latitude + 0.1;
        double lngMin = longitude - 0.1;
        double lngMax = longitude + 0.1;

        return hospitalRepository.findNearbyHospitals(latMin, latMax, lngMin, lngMax, disease);
    }
    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hospital not found"));
    }

    public Hospital updateHospital(Long id, Hospital hospital) {
        Hospital existingHospital = hospitalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hospital not found"));
        existingHospital.setName(hospital.getName());
        existingHospital.setAddress(hospital.getAddress());
        existingHospital.setLatitude(hospital.getLatitude());
        existingHospital.setLongitude(hospital.getLongitude());
        existingHospital.setRating(hospital.getRating());
        return hospitalRepository.save(existingHospital);
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

}
