package com.torin.votersapp.controllers;

import com.torin.votersapp.dto.ElectionTypeRequest;
import com.torin.votersapp.services.IElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/election")
public class ElectionController {
    private final IElectionService electionService;

    public ResponseEntity<?> getElectionWinner(@RequestBody ElectionTypeRequest electionTypeRequest){
        return new ResponseEntity<>(electionService.getElectionWinner(electionTypeRequest), HttpStatus.OK);
    }
}
