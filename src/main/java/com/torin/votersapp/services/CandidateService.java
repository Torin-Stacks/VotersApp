package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.data.models.PoliticalParty;
import com.torin.votersapp.data.models.Voter;
import com.torin.votersapp.data.repository.CandidateRepository;
import com.torin.votersapp.dto.*;
import com.torin.votersapp.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CandidateService implements ICandidateService{
    private final CandidateRepository candidateRepository;
    private final IPoliticalPartyService iPoliticalService;

    @Override
    public List<Candidate> findAllCandidatesForAParticularElection(ElectionTypeRequest electionTypeRequest) {
        return candidateRepository.findByElection_Type(electionTypeRequest.getElectionType());
    }

    @Override
    public ResponseEntity<?> receiveCastedVote(CandidateChoiceRequest candidateChoiceRequest) {
     Candidate foundCandidate =   candidateRepository.findByCandidateIdentificationNumber(candidateChoiceRequest.getCandidateIdentificationNumber());
     if(foundCandidate == null){
         throw new CandidateNotFoundException("you have entered the wrong candidate Identification number");
     }
     foundCandidate.setNumberOfVotes(foundCandidate.getNumberOfVotes()+1);
     //am I supposed to update here?
     return new ResponseEntity<>("You have voted", HttpStatus.OK);

    }

    @Override
    public Candidate getElectionWinner(ElectionTypeRequest electionTypeRequest) {
        List<Candidate> listOfAllCandidatesForParticularElectionTYpe = findAllCandidatesForAParticularElection(electionTypeRequest);
        long maxVote = 0;
        Candidate candidateWithHighestVote = null;

        for(int i = 0; i < listOfAllCandidatesForParticularElectionTYpe.size();i++){
            if(listOfAllCandidatesForParticularElectionTYpe.get(i).getNumberOfVotes() > maxVote){
                maxVote = listOfAllCandidatesForParticularElectionTYpe.get(i).getNumberOfVotes();
            }
        }

        for(int i = 0; i < listOfAllCandidatesForParticularElectionTYpe.size();i++){
            if(listOfAllCandidatesForParticularElectionTYpe.get(i).getNumberOfVotes() == maxVote){
              candidateWithHighestVote =  listOfAllCandidatesForParticularElectionTYpe.get(i);
            }
        }
        return candidateWithHighestVote;

    }

    @Override
    public CandidateRegistrationResponse register(CandidateRegistrationRequest candidateRegistrationRequest) {
        CandidateRegistrationResponse candidateRegistrationResponse = new CandidateRegistrationResponse();
        List<PoliticalParty> politicalParties = iPoliticalService.findAllPoliticalParties();
        for(int i = 0; i < politicalParties.size(); i++){
            if(!candidateRegistrationRequest.getPoliticalPartyIdentificationNumber().equals(politicalParties.get(i).getPoliticalPartyIdentificationNumber())){
              throw new IllegibleCandidateException("you have provided a wrong party id");
            }
        }
            candidateRegistrationResponse.setEligibilityStatus("you are eligible to run as candidate" );

        Candidate newCandidate = new Candidate();
        newCandidate.setFirstName(candidateRegistrationRequest.getFirstName());

        newCandidate.setLastName(candidateRegistrationRequest.getLastName());
        newCandidate.setEmail(candidateRegistrationRequest.getEmail());
        newCandidate.setPassword(candidateRegistrationRequest.getPassword());
        newCandidate.setAddress(candidateRegistrationRequest.getAddress());
        newCandidate.setGender(candidateRegistrationRequest.getGender());

        if(Integer.parseInt(candidateRegistrationRequest.getBirthDate()) < 1 || Integer.parseInt(candidateRegistrationRequest.getBirthDate()) > 31){
            throw new WrongBirthDateException("Enter correct date of birth");
        }
        newCandidate.setBirthDate(candidateRegistrationRequest.getBirthDate());

        if(Integer.parseInt(candidateRegistrationRequest.getBirthMonth()) < 1 || Integer.parseInt(candidateRegistrationRequest.getBirthDate()) > 12){
            throw new WrongBirthMonthException("Enter correct month of birth");
        }
        newCandidate.setBirthMonth(candidateRegistrationRequest.getBirthMonth());

        newCandidate.setBirthYear(candidateRegistrationRequest.getBirthYear());

        SecureRandom generatedCardNumber= new SecureRandom();
        String generatedValue = String.valueOf(generatedCardNumber.nextInt(1000000,9999999));
        newCandidate.setCandidateIdentificationNumber(generatedValue);
        newCandidate.setPoliticalParty(candidateRegistrationRequest.getPoliticalParty());
        newCandidate.setPoliticalPartyIdentificationNumber(candidateRegistrationRequest.getPoliticalPartyIdentificationNumber());
        newCandidate.setElection(candidateRegistrationRequest.getElection());
        candidateRepository.save(newCandidate);

        candidateRegistrationResponse.setCandidateIdentificationNumber(generatedValue);
        return  candidateRegistrationResponse;

    }

    @Override
    public CandidateLoginResponse login(CandidateLoginRequest candidateLoginRequest) {

            Candidate foundCandidate = candidateRepository.findByEmail(candidateLoginRequest.getEmail());
            if(foundCandidate == null){
                throw new CandidateNotFoundException("there is no candidate with this username or password");
            }
            if(!foundCandidate.getPassword().equals(candidateLoginRequest.getPassword())){
                throw new WrongPasswordException("Enter the right password");
            }
            if(!foundCandidate.getCandidateIdentificationNumber().equals(candidateLoginRequest.getCandidateIdentificationNumber())){
                throw new WrongCandidateIdentificationNumberException("Enter the correct candidate identification Number");
            }
            return new CandidateLoginResponse().builder()
                    .response("welcome")
                    .build();

    }


}
