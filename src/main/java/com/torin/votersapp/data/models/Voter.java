package com.torin.votersapp.data.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
//    @Column(nullable = false)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    private Address address;
    @Column(nullable = false)
    private String birthDate;
    @Column(nullable = false)
    private String birthMonth;
    @Column(nullable = false)
    private String birthYear;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String gender;
    private boolean canVote;
    private String votersCardNumber;


}
