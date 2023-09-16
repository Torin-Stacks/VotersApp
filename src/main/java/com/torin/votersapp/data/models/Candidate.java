package com.torin.votersapp.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Candidate {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
//    @Column(nullable = false)
    @OneToOne( cascade = CascadeType.ALL)
    private Address address;
    @Column(nullable = false)
    private String birthDate;
    @Column(nullable = false)
    private String birthMonth;
    @Column(nullable = false)
    private String birthYear;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String gender;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private PoliticalParty politicalParty;

    @OneToOne
    private Election election;

    private long numberOfVotes;
}
