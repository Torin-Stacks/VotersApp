package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.data.models.Voter;
import com.torin.votersapp.dto.*;
import com.torin.votersapp.exceptions.WrongBirthDateException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVoterService {
    VoterRegistrationResponse register(VoterRegistrationRequest voterRegistrationRequest) throws WrongBirthDateException;

    VoterLoginResponse login(VoterLoginRequest voterLoginRequest);

    List<Candidate> getCandidateListByElectionType(ElectionTypeRequest electionTypeRequest);

    ResponseEntity<?> castVote(CandidateChoiceRequest candidateChoiceRequest);


    Voter findVoterByVoterEmail(String email);

    Voter findVoterByVotersCardNumber(String votersCardNumber);

    String updateVoter(Long id,UpdateVoterRequest updateVoterRequest);

    void save(Voter foundVoter);

    List<Candidate> getCandidateListByElectionIdentificationNumber(ElectionByIdRequest electionByIdRequest);
}

