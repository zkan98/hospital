package com.example.hospitalbackend.hospital.entity;

import com.example.hospitalbackend.review.entity.Review;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = true)
    private double rating;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
