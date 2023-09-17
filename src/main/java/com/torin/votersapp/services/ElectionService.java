package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.dto.ElectionTypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectionService implements IElectionService {
   private final ICandidateService iCandidateService;
    @Override
    public Candidate getElectionWinner(ElectionTypeRequest electionTypeRequest) {
        return iCandidateService.getElectionWinner(electionTypeRequest);
    }
}
