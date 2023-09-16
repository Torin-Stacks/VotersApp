package com.torin.votersapp.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String town;

    @Column(nullable = false)
    private String localGovernmentArea;

    @Column(nullable = false)
    private String state;

}
