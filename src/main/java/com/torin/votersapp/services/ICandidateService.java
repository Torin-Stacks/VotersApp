package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICandidateService {
    List<Candidate> findAllCandidatesForAParticularElection(ElectionTypeRequest electionTypeRequest);

    ResponseEntity<?> receiveCastedVote(CandidateChoiceRequest candidateChoiceRequest);

    Candidate getElectionWinner(ElectionTypeRequest electionTypeRequest);

    CandidateRegistrationResponse register(CandidateRegistrationRequest candidateRegistrationRequest);

    CandidateLoginResponse login(CandidateLoginRequest candidateLoginRequest);
}

