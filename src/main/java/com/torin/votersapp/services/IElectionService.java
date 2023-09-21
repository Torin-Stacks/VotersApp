package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.dto.ElectionByIdRequest;
import com.torin.votersapp.dto.ElectionCreationRequest;
import com.torin.votersapp.dto.ElectionCreationResponse;
import com.torin.votersapp.dto.ElectionTypeRequest;

public interface IElectionService {
    Candidate getElectionWinner(ElectionByIdRequest electionByIdRequest);

    ElectionCreationResponse register(ElectionCreationRequest electionCreationRequest);
}
