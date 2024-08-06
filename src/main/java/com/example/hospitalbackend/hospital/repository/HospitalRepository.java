package com.example.hospitalbackend.hospital.repository;// HospitalRepository.java

import com.example.hospitalbackend.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    @Query("SELECT h FROM Hospital h WHERE h.latitude BETWEEN :latMin AND :latMax AND h.longitude BETWEEN :lngMin AND :lngMax AND h.name LIKE %:disease%")
    List<Hospital> findNearbyHospitals(@Param("latMin") double latMin, @Param("latMax") double latMax, @Param("lngMin") double lngMin, @Param("lngMax") double lngMax, @Param("disease") String disease);
}
