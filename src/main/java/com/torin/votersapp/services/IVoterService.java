package com.torin.votersapp.services;

import com.torin.votersapp.dto.VoterRegistrationRequest;
import com.torin.votersapp.dto.VoterRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface IVoterService {
    VoterRegistrationResponse register(VoterRegistrationRequest voterRegistrationRequest);
}
