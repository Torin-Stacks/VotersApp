package com.torin.votersapp.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Election")

public class Election {
    @Column(nullable = false)
    private String type;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;
    private boolean electionStillOn;

    private String winner;
    private String winnersparty;
    private String ElectionIdentificationNumber;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Candidate> electionCandidates;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
