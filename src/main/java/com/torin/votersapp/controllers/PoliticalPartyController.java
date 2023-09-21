package com.torin.votersapp.controllers;

import com.torin.votersapp.dto.*;
import com.torin.votersapp.services.IPoliticalPartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/political_party")
public class PoliticalPartyController {
    private final IPoliticalPartyService iPoliticalPartyService;

    @PostMapping("/register_political_party")
    public PoliticalPartyRegistrationResponse register(@RequestBody PoliticalPartyRegistrationRequest politicalPartyRegistrationRequest){
        return iPoliticalPartyService.register(politicalPartyRegistrationRequest);
    }

    @GetMapping("/political_parties")
    public ResponseEntity<?> getAllPoliticalParties(){
        return new ResponseEntity<>(iPoliticalPartyService.findAllPoliticalParties(), HttpStatus.OK);
    }
    @PostMapping("/update_political_Party")
    public PoliticalPartyUpdateResponse Update (@RequestBody PoliticalPartyUpdateRequest politicalPartyUpdateRequest){
        return iPoliticalPartyService.update(politicalPartyUpdateRequest);

    }

}
