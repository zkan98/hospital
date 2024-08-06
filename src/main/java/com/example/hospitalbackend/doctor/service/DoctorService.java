package com.example.hospitalbackend.doctor.service;

import com.example.hospitalbackend.doctor.entity.Doctor;
import com.example.hospitalbackend.doctor.repository.DoctorRepository;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor not found"));
        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialty(doctor.getSpecialty());
        existingDoctor.setRating(doctor.getRating());
        return doctorRepository.save(existingDoctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
