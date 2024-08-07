package com.example.hospitalbackend.recommendation.entity;

import com.example.hospitalbackend.hospital.entity.Hospital;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private String disease;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;
}
