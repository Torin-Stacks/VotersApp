package com.torin.votersapp.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PoliticalParty {
    @Column(unique = true,nullable = false)
    String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
