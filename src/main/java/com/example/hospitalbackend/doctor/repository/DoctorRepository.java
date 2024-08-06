package com.example.hospitalbackend.doctor.repository;

import com.example.hospitalbackend.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
