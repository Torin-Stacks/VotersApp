package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Address;
import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.data.models.Election;
import com.torin.votersapp.data.models.PoliticalParty;
import com.torin.votersapp.data.repository.CandidateRepository;
import com.torin.votersapp.dto.CandidateRegistrationRequest;
import com.torin.votersapp.dto.ElectionByIdRequest;
import com.torin.votersapp.exceptions.IllegibleCandidateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class CandidateServiceTest {
    @Autowired
    CandidateService candidateService;
    @Autowired
    CandidateRepository candidateRepository;
    @Test


    public void testCandidateCanRegister(){
        CandidateRegistrationRequest candidateRegistrationRequest =CandidateRegistrationRequest.builder()
                .firstName("master")
                .lastName("maetrt")
                .email("master@gmail.com")
                .address(new Address().builder()
                        .houseNumber("23")
                        .street("anthony")
                        .localGovernmentArea("kosofe")
                        .town("kosofe")
                        .state("lagos")
                        .build())
                .gender("female")
                .password("shile")
                .birthDate("1")
                .birthMonth("2")
                .birthYear("1990")
                .electionType("gubernatorial")
                .ElectionIdentificationNumber("58468282gub")
                .politicalPartyIdentificationNumber("5520")
                .build();

        candidateService.register(candidateRegistrationRequest);
    }

    @Test
    public void testOnlyOneCandidateCanCanRegisterUnderAPoliticalPartyForAParticularElection(){
        CandidateRegistrationRequest candidateRegistrationRequest =CandidateRegistrationRequest.builder()
                .firstName("tareeq")
                .lastName("faizah")
                .email("tareeq@gmail.com")
                .address(new Address().builder()
                        .houseNumber("23")
                        .street("anthony")
                        .localGovernmentArea("kosofe")
                        .town("kosofe")
                        .state("lagos")
                        .build())
                .gender("female")
                .password("tariri")
                .birthDate("1")
                .birthMonth("2")
                .birthYear("1990")
                .electionType("gubernatorial")
                .ElectionIdentificationNumber("58468282gub")
                .politicalPartyIdentificationNumber("5520")
                .build();


        Exception exception= assertThrows(IllegibleCandidateException.class,()->candidateService.register(candidateRegistrationRequest));
        assertEquals("We cannot have two candidates from the same party running for the same position in the same election",exception.getMessage());

    }

    @Test
    public void getElectionWinner(){
        ElectionByIdRequest electionByIdRequest = ElectionByIdRequest.builder()
                .electionIdentificationNumber("58468282gub")
                .build();

        Candidate winner = candidateService.getElectionWinner(electionByIdRequest);
        String name = "ned ned";
        assertEquals(name, winner.getFirstName()+ " " +winner.getLastName());

    }

}




















