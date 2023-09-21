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
    public List<Candidate> findAllCandidatesForAParticularElectionWithElectionType(ElectionTypeRequest electionTypeRequest) {
        return candidateRepository.findByElectionType(electionTypeRequest.getElectionType());
    }

//    @Override
//    public ResponseEntity<?> receiveCastedVote(CandidateChoiceRequest candidateChoiceRequest) {
//     Candidate foundCandidate =   candidateRepository.findByCandidateIdentificationNumber(candidateChoiceRequest.getCandidateIdentificationNumber());
//     Voter foundVoter = iVoterService.findVoterByVotersCardNumber(candidateChoiceRequest.getVoterCardNumber());
//
//     if(foundCandidate == null || foundVoter.getHasVoted() == true){
//         throw new CandidateNotFoundException("you cannot vote, candidate not found");
//     }
//     foundCandidate.setNumberOfVotes(foundCandidate.getNumberOfVotes()+1);
//     candidateRepository.save(foundCandidate);
//      foundVoter.setHasVoted(true);
//      iVoterService.save(foundVoter);
//     return new ResponseEntity<>("You have voted", HttpStatus.OK);
//
//    }

    @Override
    public Candidate getElectionWinner(ElectionByIdRequest electionByIdRequest) {
        List<Candidate> listOfAllCandidatesForParticularElectionByIdNumber = findAllCandidatesForAParticularElectionWithElectionIdentificationNumber(electionByIdRequest);
        long maxVote = 0;
        Candidate candidateWithHighestVote = null;

        for(int i = 0; i < listOfAllCandidatesForParticularElectionByIdNumber.size();i++){
            if(listOfAllCandidatesForParticularElectionByIdNumber.get(i).getNumberOfVotes() > maxVote){
                maxVote = listOfAllCandidatesForParticularElectionByIdNumber.get(i).getNumberOfVotes();
            }
        }

        for(int i = 0; i < listOfAllCandidatesForParticularElectionByIdNumber.size();i++){
            if(listOfAllCandidatesForParticularElectionByIdNumber.get(i).getNumberOfVotes() == maxVote){
              candidateWithHighestVote =  listOfAllCandidatesForParticularElectionByIdNumber.get(i);
            }
        }
        return candidateWithHighestVote;

    }

    @Override
    public CandidateRegistrationResponse register(CandidateRegistrationRequest candidateRegistrationRequest) throws WrongBirthDateException {
        CandidateRegistrationResponse candidateRegistrationResponse = new CandidateRegistrationResponse();
        List<PoliticalParty> politicalParties = iPoliticalService.findAllPoliticalParties();
        for(int i = 0; i < politicalParties.size(); i++){
            if(!candidateRegistrationRequest.getPoliticalPartyIdentificationNumber().equals(politicalParties.get(i).getPoliticalPartyIdentificationNumber())){
              throw new IllegibleCandidateException("you have provided a wrong party id");
            }
        }
            candidateRegistrationResponse.setEligibilityStatus("you are eligible to run as candidate" );
        List<Candidate> foundCandidates = findCandidatesByElectionIdentificationNumber(candidateRegistrationRequest.getElectionIdentificationNumber());
        for(int i=0; i<foundCandidates.size();i++){
            if(foundCandidates.get(i).getPoliticalPartyIdentificationNumber().equals(candidateRegistrationRequest.getPoliticalPartyIdentificationNumber())&&
                    foundCandidates.get(i).getElectionType().equals(candidateRegistrationRequest.getElectionType())){
                throw new IllegibleCandidateException("We cannot have two candidates from the same party running for the same position in the same election");
            }
        }

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

        SecureRandom generatedCandidateIdNumber= new SecureRandom();
        String generatedValue = String.valueOf(generatedCandidateIdNumber.nextInt(1000000,9999999));
        newCandidate.setElectionType(candidateRegistrationRequest.getElectionType());
        newCandidate.setCandidateIdentificationNumber(generatedValue);
        newCandidate.setPoliticalPartyIdentificationNumber(candidateRegistrationRequest.getPoliticalPartyIdentificationNumber());
        newCandidate.setElectionIdentificationNumber(candidateRegistrationRequest.getElectionIdentificationNumber());
        candidateRepository.save(newCandidate);

        candidateRegistrationResponse.setCandidateIdentificationNumber(generatedValue);
        return  candidateRegistrationResponse;

    }

    private List<Candidate> findCandidatesByElectionIdentificationNumber(String electionIdentificationNumber) {
        return candidateRepository.findByElectionIdentificationNumber(electionIdentificationNumber);
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

    @Override
    public Candidate findCandidate(String candidateIdentificationNumber) {
        return candidateRepository.findByCandidateIdentificationNumber(candidateIdentificationNumber);
    }

    @Override
    public List<Candidate> findAllCandidatesForAParticularElectionWithElectionIdentificationNumber(ElectionByIdRequest electionByIdRequest) {
        return candidateRepository.findByElectionIdentificationNumber(electionByIdRequest.getElectionIdentificationNumber());
    }

    @Override
    public void save(Candidate foundCandidate) {

        candidateRepository.save(foundCandidate);
    }


}
