package com.torin.votersapp.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Candidate")

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
    private String password;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String electionType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String politicalPartyIdentificationNumber;
    private String electionIdentificationNumber;
    private String candidateIdentificationNumber;
    private long numberOfVotes;

    //    @OneToOne( cascade = CascadeType.ALL)
//    private PoliticalParty politicalParty;

    //    @OneToOne( cascade = CascadeType.ALL)
//    private Election election;
}
