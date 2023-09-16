package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Voter;
import com.torin.votersapp.data.repository.VoterRepository;
import com.torin.votersapp.dto.VoterRegistrationRequest;
import com.torin.votersapp.dto.VoterRegistrationResponse;
import com.torin.votersapp.exceptions.WrongBirthDateException;
import com.torin.votersapp.exceptions.WrongBirthMonthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Year;

@Service
@RequiredArgsConstructor

public class VoterService implements IVoterService{

    private final VoterRepository voterRepository;
    @Override
    public VoterRegistrationResponse register(VoterRegistrationRequest voterRegistrationRequest) {
        int year = Year.now().getValue();

        if(year-Integer.parseInt(voterRegistrationRequest.getBirthYear()) < 18){
            return new VoterRegistrationResponse().builder()
                    .eligibilityStatus("you are ineligible to vote")
                    .build();
        }

        Voter newVoter = new Voter();
        newVoter.setFirstName(voterRegistrationRequest.getFirstName());
        newVoter.setLastName(voterRegistrationRequest.getLastName());
        newVoter.setEmail(voterRegistrationRequest.getEmail());
        newVoter.setAddress(voterRegistrationRequest.getAddress());
        newVoter.setGender(voterRegistrationRequest.getGender());
        if(Integer.parseInt(voterRegistrationRequest.getBirthDate()) < 1 || Integer.parseInt(voterRegistrationRequest.getBirthDate()) > 31){
           throw new WrongBirthDateException("Enter correct date of birth");
        }
        newVoter.setBirthDate(voterRegistrationRequest.getBirthDate());
        if(Integer.parseInt(voterRegistrationRequest.getBirthMonth()) < 1 || Integer.parseInt(voterRegistrationRequest.getBirthDate()) > 12){
            throw new WrongBirthMonthException("Enter correct month of birth");
        }
        newVoter.setBirthMonth(voterRegistrationRequest.getBirthMonth());
        newVoter.setBirthYear(voterRegistrationRequest.getBirthYear());

        SecureRandom generatedCardNumber= new SecureRandom();
        String generatedValue = String.valueOf(generatedCardNumber.nextInt(100000,999999)) + voterRegistrationRequest.getLastName().substring(0,3);
        newVoter.setVotersCardNumber(generatedValue);
        voterRepository.save(newVoter);

        return  VoterRegistrationResponse.builder()
                .voterCardNumber(generatedValue)
                .build();

}}
