package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Address;
import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.data.repository.VoterRepository;
import com.torin.votersapp.dto.*;
import com.torin.votersapp.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest


class VoterServiceTest {
@Autowired
private VoterService voterService;
@Autowired
private VoterRepository voterRepository;
@Autowired
private CandidateService candidateService;
@Test
public void testBelow18yrsCannotRegister() throws WrongBirthDateException {

    VoterRegistrationRequest voterRegistrationRequest = VoterRegistrationRequest.builder()
            .firstName("Vicky")
            .lastName("Tom")
            .password("1234")
            .email("vicky@gmail.com")
            .gender("female")
            .birthDate("1")
            .birthMonth("2")
            .birthYear("2007")
            .address(new Address().builder()
                    .houseNumber("7")
                    .street("Mac")
                    .town("satelite")
                    .localGovernmentArea("ikoyi")
                    .state("lagos")
                    .build())
            .build();
    VoterRegistrationResponse voterRegistrationResponse = new VoterRegistrationResponse();

   VoterRegistrationResponse actualResponse = voterService.register(voterRegistrationRequest);

   VoterRegistrationResponse expectedResponse = voterRegistrationResponse.builder()
           .eligibilityStatus("you are ineligible to vote")
           .build();

    assertEquals(expectedResponse,actualResponse);

}

@Test
    public void testCannotAcceptAnyNumberGreaterThan310rLesserThan0forBirthdate(){
        VoterRegistrationRequest voterRegistrationRequest = VoterRegistrationRequest.builder()
                .firstName("Vicky")
                .lastName("Tom")
                .password("1234")
                .email("vicky@gmail.com")
                .gender("female")
                .birthDate("0")
                .birthMonth("2")
                .birthYear("1998")
                .address(new Address().builder()
                        .houseNumber("7")
                        .street("Mac")
                        .town("satelite")
                        .localGovernmentArea("ikoyi")
                        .state("lagos")
                        .build())
                .build();
    System.out.println(voterRegistrationRequest);

        Exception exception = assertThrows(WrongBirthDateException.class, ()->voterService.register(voterRegistrationRequest));
        assertEquals("Enter correct date of birth", exception.getMessage());

    }

    @Test
    public void testCannotAcceptAnyNumberGreaterThan120rLesserThan0forBirthMonth(){
        VoterRegistrationRequest voterRegistrationRequest = VoterRegistrationRequest.builder()
                .firstName("Vicky")
                .lastName("Tom")
                .password("1234")
                .email("vicky@gmail.com")
                .gender("female")
                .birthDate("31")
                .birthMonth("22")
                .birthYear("1998")
                .address(new Address().builder()
                        .houseNumber("7")
                        .street("Mac")
                        .town("satelite")
                        .localGovernmentArea("ikoyi")
                        .state("lagos")
                        .build())
                .build();


        Exception exception = assertThrows(WrongBirthMonthException.class, ()->voterService.register(voterRegistrationRequest));
        assertEquals("Enter correct month of birth", exception.getMessage());

    }

    @Test
    public void testRegisterMethod() throws WrongBirthDateException {
        VoterRegistrationRequest voterRegistrationRequest = VoterRegistrationRequest.builder()
                .firstName("Vicky")
                .lastName("Tom")
                .password("1234")
                .email("vicky@gmail.com")
                .gender("female")
                .birthDate("31")
                .birthMonth("12")
                .birthYear("1998")
                .address(new Address().builder()
                        .houseNumber("7")
                        .street("Mac")
                        .town("satelite")
                        .localGovernmentArea("ikoyi")
                        .state("lagos")
                        .build())
                .build();
        voterService.register(voterRegistrationRequest);


    }

    @Test
    public void testCannotLoginWIthWrongPassword(){
        VoterLoginResponse voterLoginResponse = VoterLoginResponse.builder()
                .response("welcome")
                .build();
        VoterLoginRequest voterLoginRequest = VoterLoginRequest.builder()
                .email("vicky@gmail.com")
                .password("1234o")
                .voterCardNumber("439528Tom")
                .build();

        Exception exception = assertThrows(WrongPasswordException.class,()->{voterService.login(voterLoginRequest);});
        assertEquals("Enter the right password", exception.getMessage());

    }

    @Test
    public void testCannotLoginWIthWrongEmail(){
        VoterLoginResponse voterLoginResponse = VoterLoginResponse.builder()
                .response("welcome")
                .build();

        VoterLoginRequest voterLoginRequest = VoterLoginRequest.builder()
                .email("vicky89@gmail.com")
                .password("1234")
                .voterCardNumber("439528Tom")
                .build();

        Exception exception = assertThrows(VoterNotFoundException.class,()->{voterService.login(voterLoginRequest);});
        assertEquals("there is no voter with this username", exception.getMessage());

    }

    @Test
    public void testLogin(){
        VoterLoginResponse voterLoginResponse = VoterLoginResponse.builder()
                .response("welcome")
                .build();

        VoterLoginRequest voterLoginRequest = VoterLoginRequest.builder()
                .email("vicky@gmail.com")
                .password("1234")
                .voterCardNumber("675314Tom")
                .build();

;
        assertEquals(voterLoginResponse,voterService.login(voterLoginRequest));

    }
    @Test
    public void testCanGetListOfAllCandidateForAParticularElectionByType(){
        ElectionTypeRequest electionTypeRequest =ElectionTypeRequest.builder()
                .electionType("gubernatorial")
                .build();
    List<Candidate> foundCandidates = voterService.getCandidateListByElectionType(electionTypeRequest);
    assertEquals(1,foundCandidates.size());
    }

    @Test
    public void testVoterCanCastVote(){
    CandidateChoiceRequest candidateChoiceRequest = CandidateChoiceRequest.builder()
            .candidateIdentificationNumber("6632515")// register candidate and get candidate id number
            .voterCardNumber("675314Tom")
            .build();

    voterService.castVote(candidateChoiceRequest);
    Candidate foundCandidate = candidateService.findCandidate(candidateChoiceRequest.getCandidateIdentificationNumber());
    assertEquals(1,foundCandidate.getNumberOfVotes());
    }



    @Test
    public void testVoterCanCastVoteOnlyOnceInOneElection(){
        VoterRegistrationRequest voterRegistrationRequest = VoterRegistrationRequest.builder()
                .firstName("Tolu")
                .lastName("kola")
                .password("0011")
                .email("kola@gmail.com")
                .gender("male")
                .birthDate("21")
                .birthMonth("10")
                .birthYear("1988")
                .address(new Address().builder()
                        .houseNumber("10")
                        .street("Mac")
                        .town("satelite")
                        .localGovernmentArea("ikoyi")
                        .state("lagos")
                        .build())
                .build();
        var response = voterService.register(voterRegistrationRequest);

       CandidateChoiceRequest candidateChoiceRequest = CandidateChoiceRequest.builder()
            .candidateIdentificationNumber("6632515")
            .voterCardNumber(response.getVoterCardNumber())
            .build();

        Candidate foundCandidate = candidateService.findCandidate(candidateChoiceRequest.getCandidateIdentificationNumber());

        voterService.castVote(candidateChoiceRequest);

        Exception exception = assertThrows(CandidateNotFoundException.class, ()->voterService.castVote(candidateChoiceRequest));
        assertEquals("you cannot vote twice", exception.getMessage());

    }






}









