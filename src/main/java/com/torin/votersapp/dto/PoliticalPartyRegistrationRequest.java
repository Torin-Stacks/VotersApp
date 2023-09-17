package com.torin.votersapp.dto;

import com.torin.votersapp.data.models.Candidate;
import lombok.Data;

import java.util.List;

@Data

public class PoliticalPartyRegistrationRequest {

    private String name;
    private List<Candidate> candidates;
}
