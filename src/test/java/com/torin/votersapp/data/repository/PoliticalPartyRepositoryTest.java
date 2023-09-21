//package com.torin.votersapp.data.repository;
//
//import com.torin.votersapp.dto.PoliticalPartyRegistrationRequest;
//import com.torin.votersapp.dto.PoliticalPartyRegistrationResponse;
//import com.torin.votersapp.services.PoliticalPartyService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class PoliticalPartyRepositoryTest {
//    @Autowired
//    PoliticalPartyRepository politicalPartyRepository;
//    @Autowired
//    PoliticalPartyService politicalPartyService;
//    @Test
//    public void testPoliticalPartyRepository(){
//        PoliticalPartyRegistrationRequest politicalPartyRegistrationRequest = PoliticalPartyRegistrationRequest.builder()
//                .name("AD")
//                .build();
//
//        PoliticalPartyRegistrationResponse expectedPoliticalPartyRegistrationResponse = PoliticalPartyRegistrationResponse.builder()
//                .response("registered successfully")
//                .politicalPartyIdentificationNumber("9188")
//                .build();
//
//        assertEquals(expectedPoliticalPartyRegistrationResponse,politicalPartyService.register(politicalPartyRegistrationRequest));
//    }
//
//}