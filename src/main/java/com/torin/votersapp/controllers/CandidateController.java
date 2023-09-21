package com.torin.votersapp.controllers;

import com.torin.votersapp.data.models.Candidate;
import com.torin.votersapp.dto.*;
import com.torin.votersapp.exceptions.WrongBirthDateException;
import com.torin.votersapp.services.ICandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/candidate")
public class CandidateController {
    private final ICandidateService iCandidateService;
    @PostMapping("/register_candidate")
    public CandidateRegistrationResponse register(@RequestBody CandidateRegistrationRequest candidateRegistrationRequest) throws WrongBirthDateException {
        return iCandidateService.register(candidateRegistrationRequest);
    }

    @PostMapping("/login_candidate")
    public CandidateLoginResponse login (@RequestBody CandidateLoginRequest candidateLoginRequest){
        return iCandidateService.login(candidateLoginRequest);
    }

    @GetMapping("/get_candidate")
    public Candidate findCandidate (@RequestBody String candidateIdentificationNumber){
        return iCandidateService.findCandidate(candidateIdentificationNumber);
    }


}
