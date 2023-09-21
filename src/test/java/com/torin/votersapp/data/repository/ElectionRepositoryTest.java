//package com.torin.votersapp.data.repository;
//
//import com.torin.votersapp.data.models.Election;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//
//class ElectionRepositoryTest {
//    @Autowired
//   private ElectionRepository electionRepository;
//    @Test
//    public void testRegister(){
//        Election newElection =Election.builder()
//                .type("gubernatorial")
//                .date(LocalDate.now())
//                .startTime(LocalTime.now())
//                .endTime(LocalTime.NOON)
//                .electionStillOn(true)
//                .build();
//        electionRepository.save(newElection);
//        Election foundElection = electionRepository.findByType("gubernatorial");
//        assertNotNull(foundElection);
//    }
//
//
//}