package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.dto.ElectionTypeRequest;

public interface IElectionService {
    Candidate getElectionWinner(ElectionTypeRequest electionTypeRequest);
}
