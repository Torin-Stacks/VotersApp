package com.torin.votersapp.controllers;

import com.torin.votersapp.dto.VoterRegistrationRequest;
import com.torin.votersapp.dto.VoterRegistrationResponse;
import com.torin.votersapp.services.IVoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/voters")
@RequiredArgsConstructor

public class VoterController {

    private final IVoterService iVoterService;
    @PostMapping("/register")
    public VoterRegistrationResponse register(VoterRegistrationRequest voterRegistrationRequest){
        return iVoterService.register(voterRegistrationRequest);
    }
}
