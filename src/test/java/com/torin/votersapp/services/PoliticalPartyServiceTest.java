package com.torin.votersapp.services;

import com.torin.votersapp.dto.PoliticalPartyRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PoliticalPartyServiceTest {

    @Autowired
    private PoliticalPartyService politicalPartyService;
    @Test

    public void testRegister(){
        PoliticalPartyRegistrationRequest politicalPartyRegistrationRequest = PoliticalPartyRegistrationRequest.builder()
                .name("AD")
                .build();
        politicalPartyService.register(politicalPartyRegistrationRequest);
    }
@Test
    public  void TestCannotRegisterSamePoliticalPartyTwice(){
        PoliticalPartyRegistrationRequest politicalPartyRegistrationRequest = PoliticalPartyRegistrationRequest.builder()
                .name("AD")
                .build();
        politicalPartyService.register(politicalPartyRegistrationRequest);

    }

}