//package com.torin.votersapp.data.repository;
//
//import com.torin.votersapp.data.models.Address;
//import com.torin.votersapp.data.models.Candidate;
//import com.torin.votersapp.data.models.Election;
//import com.torin.votersapp.data.models.PoliticalParty;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//@RequiredArgsConstructor
//class CandidateRepositoryTest {
//@Autowired
//  private CandidateRepository candidateRepository;
//@Test
//    public void testCandidateRepository(){
//        Candidate candidateA = new Candidate();
//        candidateA.setFirstName("Shile");
//        candidateA.setLastName("Ola");
//        candidateA.setElection(new Election().builder()
//                        .type("gubernatorial")
//                .date(LocalDate.now())
//                .startTime(LocalTime.now())
//                .endTime(LocalTime.NOON)
//                .electionStillOn(true)
//                .build());
//        candidateA.setPoliticalParty(new PoliticalParty().builder()
//                        .name("AD")
//                        .politicalPartyIdentificationNumber("9188")
//                .build());
//        candidateA.setPoliticalPartyIdentificationNumber("9188");
//        candidateA.setAddress(new Address().builder()
//                        .houseNumber("2")
//                        .street("bams")
//                        .town("kosofe")
//                        .localGovernmentArea("kosofe")
//                        .state("lagos")
//                .build());
//        candidateA.setGender("female");
//        candidateA.setEmail("sileola@gmail.com");
//        candidateA.setPassword("sile");
//        candidateA.setBirthDate("1");
//        candidateA.setBirthMonth("2");
//        candidateA.setBirthYear("1990");
//
//        candidateRepository.save(candidateA);
//        Candidate candidateFound = candidateRepository.findByEmail("sileola@gmail.com");
//
//        assertNotNull(candidateFound);
//
//
//    }
//
//}