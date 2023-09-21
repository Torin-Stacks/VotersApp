//package com.torin.votersapp.data.repository;
//
//import com.torin.votersapp.data.models.Address;
//import com.torin.votersapp.data.models.Voter;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//
//class VoterRepositoryTest {
//   @Autowired
//   private VoterRepository voterRepository;
//@Test
//public void testRepository(){
//        Voter voterA =new Voter();
//        voterA.setFirstName("Vicky");
//        voterA.setLastName("Tom");
//        voterA.setPassword("1234");
//        voterA.setEmail("vicky@gmail.com");
//        voterA.setGender("female");
//        voterA.setBirthDate("1");
//        voterA.setBirthMonth("2");
//        voterA.setBirthYear("1998");
//        voterA.setAddress(Address.builder()
//                .houseNumber("7")
//                .street("Mac")
//                        .town("satelite")
//                        .localGovernmentArea("ikoyi")
//                        .state("lagos")
//                .build());
//        voterA.setVotersCardNumber("678986Vic");
//        voterRepository.save(voterA);
//        Voter foundVoter = voterRepository.findByEmail("vicky@gmail.com");
//        assertNotNull(foundVoter);
//    }
//
//
//
//
//}