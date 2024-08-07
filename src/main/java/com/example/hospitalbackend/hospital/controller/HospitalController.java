package com.example.hospitalbackend.hospital.controller;

import com.example.hospitalbackend.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchHospitalData() {
        hospitalService.fetchAndSaveHospitalData();
        return ResponseEntity.ok("Hospital data fetched and saved successfully");
    }
}
