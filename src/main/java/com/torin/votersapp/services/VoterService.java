package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.data.models.Voter;
import com.torin.votersapp.data.repository.VoterRepository;
import com.torin.votersapp.dto.*;
import com.torin.votersapp.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;


import java.security.SecureRandom;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class VoterService implements IVoterService{

    private final VoterRepository voterRepository;
    private final ICandidateService iCandidateService;

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
        newVoter.setPassword(voterRegistrationRequest.getPassword());
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

}

    @Override
    public VoterLoginResponse login(VoterLoginRequest voterLoginRequest) {
        Voter foundVoter = voterRepository.findByEmail(voterLoginRequest.getEmail());
        if(foundVoter == null){
            throw new VoterNotFoundException("there is no voter with this username or password");
        }
        if(!foundVoter.getPassword().equals(voterLoginRequest.getPassword())){
            throw new WrongPasswordException("Enter the right password");
        }
        if(!foundVoter.getVotersCardNumber().equals(voterLoginRequest.getVoterCardNumber())){
            throw new WrongVoterCardNumberException("Enter the correct  voter's Card Number");
        }
        return new VoterLoginResponse().builder()
                .response("welcome")
                .build();
    }

    @Override
    public List<Candidate> getCandidateList(ElectionTypeRequest electionTypeRequest) {
     return iCandidateService.findAllCandidatesForAParticularElection(electionTypeRequest);

    }

    @Override
    public ResponseEntity<?> castVote(CandidateChoiceRequest candidateChoiceRequest) {
        return iCandidateService.receiveCastedVote(candidateChoiceRequest);

    }

    @Override
    public Voter findVoterByVoterEmail(String email) {
        return voterRepository.findByEmail(email);
    }

    @Override
    public Voter findVoterByVotersCardNumber(String votersCardNumber) {
        return voterRepository.findByVotersCardNumber(votersCardNumber);
    }

    @Override
    public String updateVoter(Long id, UpdateVoterRequest updateVoterRequest) {
        Voter foundVoter = voterRepository.findById(id).orElseThrow(()->{return new VoterNotFoundException("Voter not found, pls enter correct id");});
        BeanUtils.copyProperties(updateVoterRequest, foundVoter);// does this really copy only the properties in the source, what happens to the generated voters card number
        voterRepository.save(foundVoter);
        return "successfully updated";
    }


}




