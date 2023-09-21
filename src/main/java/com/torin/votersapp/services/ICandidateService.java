package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.dto.*;
import com.torin.votersapp.exceptions.WrongBirthDateException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICandidateService {
    List<Candidate> findAllCandidatesForAParticularElectionWithElectionType(ElectionTypeRequest electionTypeRequest);

//    ResponseEntity<?> receiveCastedVote(CandidateChoiceRequest candidateChoiceRequest);


    Candidate getElectionWinner(ElectionByIdRequest electionByIdRequest);

    CandidateRegistrationResponse register(CandidateRegistrationRequest candidateRegistrationRequest) throws
            WrongBirthDateException;

    CandidateLoginResponse login(CandidateLoginRequest candidateLoginRequest);

    Candidate findCandidate(String candidateIdentificationNumber);

    List<Candidate> findAllCandidatesForAParticularElectionWithElectionIdentificationNumber(ElectionByIdRequest electionByIdRequest);


    void save(Candidate foundCandidate);
}

