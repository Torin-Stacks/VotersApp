package com.torin.votersapp.controllers;

import com.torin.votersapp.dto.*;
import com.torin.votersapp.services.ICandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/candidate")
public class CandidateController {
    private final ICandidateService iCandidateService;
    @PostMapping("/register_candidate")
    public CandidateRegistrationResponse register(@RequestBody CandidateRegistrationRequest candidateRegistrationRequest){
        return iCandidateService.register(candidateRegistrationRequest);
    }

    @PostMapping("/login_candidate")
    public CandidateLoginResponse login (@RequestBody CandidateLoginRequest candidateLoginRequest){
        return iCandidateService.login(candidateLoginRequest);
    }

}
