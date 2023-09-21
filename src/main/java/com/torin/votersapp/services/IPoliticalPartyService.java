package com.torin.votersapp.services;

import com.torin.votersapp.data.models.PoliticalParty;
import com.torin.votersapp.dto.PoliticalPartyRegistrationRequest;
import com.torin.votersapp.dto.PoliticalPartyRegistrationResponse;
import com.torin.votersapp.dto.PoliticalPartyUpdateRequest;
import com.torin.votersapp.dto.PoliticalPartyUpdateResponse;

import java.util.List;

public interface IPoliticalPartyService {
    List<PoliticalParty> findAllPoliticalParties();

    PoliticalPartyRegistrationResponse register(PoliticalPartyRegistrationRequest politicalPartyRegistrationRequest);


    PoliticalPartyUpdateResponse update(PoliticalPartyUpdateRequest politicalPartyUpdateRequest);
}


