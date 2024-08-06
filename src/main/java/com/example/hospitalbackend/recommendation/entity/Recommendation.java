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
    private double score;  // 추천 점수

    @Column(nullable = false)
    private String disease;  // 질병 종류

    // 추가 필드 필요시 여기에 추가
}
