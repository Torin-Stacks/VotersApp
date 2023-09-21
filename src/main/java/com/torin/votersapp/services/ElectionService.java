package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.data.models.Election;
import com.torin.votersapp.data.repository.ElectionRepository;
import com.torin.votersapp.dto.ElectionByIdRequest;
import com.torin.votersapp.dto.ElectionCreationRequest;
import com.torin.votersapp.dto.ElectionCreationResponse;
import com.torin.votersapp.dto.ElectionTypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class ElectionService implements IElectionService {
   private final ICandidateService iCandidateService;
   private final ElectionRepository electionRepository;
    @Override
    public Candidate getElectionWinner(ElectionByIdRequest electionByIdRequest) {
        return iCandidateService.getElectionWinner(electionByIdRequest);
    }

    @Override
    public ElectionCreationResponse register(ElectionCreationRequest electionCreationRequest) {
        SecureRandom generatedCandidateIdNumber= new SecureRandom();
        String generatedValue = String.valueOf(generatedCandidateIdNumber.nextInt(10000000,99999999)) + electionCreationRequest.getType().substring(0,3);

        Election newElection = new Election();
        newElection.setType(electionCreationRequest.getType());
        newElection.setDate(electionCreationRequest.getDate());
        newElection.setStartTime(electionCreationRequest.getStartTime());
        newElection.setEndTime(electionCreationRequest.getEndTime());
        newElection.setElectionStillOn(electionCreationRequest.isElectionStillOn());
        newElection.setElectionIdentificationNumber(generatedValue);
        electionRepository.save(newElection);

        return ElectionCreationResponse.builder()
                .response("Election successfully registered/started")
                .build();
    }
}
