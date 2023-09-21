package com.torin.votersapp.services;

import com.torin.votersapp.data.models.Election;
import com.torin.votersapp.dto.ElectionCreationRequest;
import com.torin.votersapp.dto.ElectionCreationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ElectionServiceTest {
    @Autowired
    private ElectionService electionService;


@Test
    public void testRegister(){

        ElectionCreationRequest electionCreationRequest = ElectionCreationRequest.builder()
                .type("gubernatorial")
                .date(LocalDate.now())
                .startTime(LocalTime.now())
                .endTime(LocalTime.NOON)
                .electionStillOn(true)
                .build();

        electionService.register(electionCreationRequest);

    }


}