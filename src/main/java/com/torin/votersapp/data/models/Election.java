package com.torin.votersapp.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Entity

public class Election {
    @Column(nullable = false,unique = true)
    private String type;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;
    private boolean electionStillOn;

    private String winner;
    private String winnersparty;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
