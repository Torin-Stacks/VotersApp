package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.data.models.Voter;
import com.torin.votersapp.data.repository.VoterRepository;
import com.torin.votersapp.dto.*;
import com.torin.votersapp.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;


import java.security.SecureRandom;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoterService implements IVoterService{

    private final VoterRepository voterRepository;
    private final ICandidateService iCandidateService;

    @Override
    public VoterRegistrationResponse register(VoterRegistrationRequest voterRegistrationRequest) throws WrongBirthDateException {
        int currentYear = Year.now().getValue();

        if(currentYear-Integer.parseInt(voterRegistrationRequest.getBirthYear()) < 18){
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
        if(Integer.parseInt(voterRegistrationRequest.getBirthMonth()) < 1 || Integer.parseInt(voterRegistrationRequest.getBirthMonth()) > 12){
            throw new WrongBirthMonthException("Enter correct month of birth");
        }
        newVoter.setBirthMonth(voterRegistrationRequest.getBirthMonth());
        newVoter.setBirthYear(voterRegistrationRequest.getBirthYear());

        SecureRandom generatedCardNumber= new SecureRandom();
        String generatedValue = String.valueOf(generatedCardNumber.nextInt(100000,999999)) + voterRegistrationRequest.getLastName().substring(0,3);
        newVoter.setVotersCardNumber(generatedValue);
        newVoter.setHasVoted(false);
        voterRepository.save(newVoter);

        return  VoterRegistrationResponse.builder()
                .voterCardNumber(generatedValue)
                .build();

}

    @Override
    public VoterLoginResponse login(VoterLoginRequest voterLoginRequest) {
        Voter foundVoter = voterRepository.findByEmail(voterLoginRequest.getEmail());
        if(foundVoter == null){
            throw new VoterNotFoundException("there is no voter with this username");
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
    public List<Candidate> getCandidateListByElectionType(ElectionTypeRequest electionTypeRequest) {
     return iCandidateService.findAllCandidatesForAParticularElectionWithElectionType(electionTypeRequest);

    }

    @Override
    public ResponseEntity<?> castVote(CandidateChoiceRequest candidateChoiceRequest) {
       Candidate foundCandidate = iCandidateService.findCandidate(candidateChoiceRequest.getCandidateIdentificationNumber());
       Voter foundVoter = findVoterByVotersCardNumber(candidateChoiceRequest.getVoterCardNumber());

            log.info(""+foundVoter.getHasVoted());
            if(foundVoter.getHasVoted()){
                throw new CandidateNotFoundException("you cannot vote twice");
            }
            if(foundCandidate == null){
            throw new CandidateNotFoundException("candidate not found");
        }
            foundCandidate.setNumberOfVotes(foundCandidate.getNumberOfVotes()+1);

            iCandidateService.save(foundCandidate);
            foundVoter.setHasVoted(true);
            voterRepository.save(foundVoter);
            return new ResponseEntity<>("You have voted", HttpStatus.OK);

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

    @Override
    public void save(Voter foundVoter) {
        voterRepository.save(foundVoter);
    }

    @Override
    public List<Candidate> getCandidateListByElectionIdentificationNumber(ElectionByIdRequest electionByIdRequest) {
        return iCandidateService.findAllCandidatesForAParticularElectionWithElectionIdentificationNumber(electionByIdRequest);
    }


}




