package com.torin.votersapp.controllers;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.dto.*;
import com.torin.votersapp.exceptions.WrongBirthDateException;
import com.torin.votersapp.services.IVoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/voters")
@RequiredArgsConstructor

public class VoterController {

    private final IVoterService iVoterService;

    @PostMapping("/register_voter")
    public VoterRegistrationResponse register(@RequestBody VoterRegistrationRequest voterRegistrationRequest) throws WrongBirthDateException {
        return iVoterService.register(voterRegistrationRequest);
    }

    @PostMapping("/login_voter")
    public VoterLoginResponse login (@RequestBody VoterLoginRequest voterLoginRequest){
        return iVoterService.login(voterLoginRequest);
    }

    @GetMapping("/candidate_by_election_type_list")
    public List<Candidate> listOfCandidatesForAParticularElectionType(@RequestBody ElectionTypeRequest electionTypeRequest){
        return iVoterService.getCandidateListByElectionType(electionTypeRequest);
    }
    @GetMapping("/candidate_by_id_list")
    public List<Candidate> listOfCandidatesForAParticularElectionByElectionId(@RequestBody ElectionByIdRequest electionByIdRequest){
        return iVoterService.getCandidateListByElectionIdentificationNumber(electionByIdRequest);
    }


    @PostMapping("/cast_vote")
    public ResponseEntity<?> electedChoice(@RequestBody CandidateChoiceRequest candidateChoiceRequest){
        return iVoterService.castVote(candidateChoiceRequest);
    }

    @GetMapping("/voter/{email}")
    public ResponseEntity<?> findVoterByVoterEmail(@PathVariable String email){
        return new ResponseEntity<>(iVoterService.findVoterByVoterEmail(email), HttpStatus.OK);
    }

    @GetMapping("/voter_card_number/{voters_card_number}")
    public ResponseEntity<?> findVoterByVotersCardNumber(@PathVariable String voters_card_number){
        return new ResponseEntity<>(iVoterService.findVoterByVotersCardNumber(voters_card_number), HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateVoter(@PathVariable Long id, @RequestBody UpdateVoterRequest updateVoterRequest){
        return new ResponseEntity<>(iVoterService.updateVoter(id, updateVoterRequest),HttpStatus.OK);
    }


}
