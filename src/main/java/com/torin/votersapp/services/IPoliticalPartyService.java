package com.torin.votersapp.services;

import com.torin.votersapp.data.models.PoliticalParty;
import com.torin.votersapp.dto.PoliticalPartyRegistrationRequest;
import com.torin.votersapp.dto.PoliticalPartyRegistrationResponse;

import java.util.List;

public interface IPoliticalPartyService {
    List<PoliticalParty> findAllPoliticalParties();

    PoliticalPartyRegistrationResponse register(PoliticalPartyRegistrationRequest politicalPartyRegistrationRequest);
}


