package com.torin.votersapp.services;

import com.torin.votersapp.data.models.PoliticalParty;
import com.torin.votersapp.data.repository.PoliticalPartyRepository;
import com.torin.votersapp.dto.PoliticalPartyRegistrationRequest;
import com.torin.votersapp.dto.PoliticalPartyRegistrationResponse;
import com.torin.votersapp.dto.PoliticalPartyUpdateRequest;
import com.torin.votersapp.dto.PoliticalPartyUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
@Service
@RequiredArgsConstructor
public class PoliticalPartyService implements IPoliticalPartyService{
    private final PoliticalPartyRepository politicalPartyRepository;


    @Override
    public List<PoliticalParty> findAllPoliticalParties() {
        return politicalPartyRepository.findAll();
    }

    @Override
    public PoliticalPartyRegistrationResponse register(PoliticalPartyRegistrationRequest politicalPartyRegistrationRequest) {

        PoliticalParty newPoliticalParty = new PoliticalParty();
        newPoliticalParty.setName(politicalPartyRegistrationRequest.getName());
        SecureRandom generatedCardNumber= new SecureRandom();
        String generatedValue = String.valueOf(generatedCardNumber.nextInt(1000,9999));
        newPoliticalParty.setPoliticalPartyIdentificationNumber(generatedValue);
        politicalPartyRepository.save(newPoliticalParty);
        return  new PoliticalPartyRegistrationResponse().builder()
                .response("registered successfully")
                .politicalPartyIdentificationNumber(generatedValue)
                .build();
    }

    @Override
    public PoliticalPartyUpdateResponse update(PoliticalPartyUpdateRequest politicalPartyUpdateRequest) {
        PoliticalParty foundParty = politicalPartyRepository.findByPoliticalPartyIdentificationNumber(politicalPartyUpdateRequest.getPoliticalPartIdentificationNumber());
        BeanUtils.copyProperties(politicalPartyUpdateRequest,foundParty);
        politicalPartyRepository.save(foundParty);
        return PoliticalPartyUpdateResponse.builder()
                .message("successfully updated")
                .build();
    }


}
