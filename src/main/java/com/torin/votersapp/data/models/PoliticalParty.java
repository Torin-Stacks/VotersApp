package com.torin.votersapp.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PoliticalParty")
public class PoliticalParty {
    @Column(unique = true, nullable = false)
    String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Candidate> candidates;
    private String politicalPartyIdentificationNumber;


}
