package com.example.hospitalbackend.user.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, name = "admin")
    private boolean isAdmin;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String phoneNumber;
}
