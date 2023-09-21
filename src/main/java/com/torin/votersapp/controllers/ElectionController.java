package com.torin.votersapp.controllers;

import com.torin.votersapp.dto.ElectionByIdRequest;
import com.torin.votersapp.dto.ElectionCreationRequest;
import com.torin.votersapp.dto.ElectionTypeRequest;
import com.torin.votersapp.services.IElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/election")
public class ElectionController {
    private final IElectionService electionService;
@GetMapping("/winner")
    public ResponseEntity<?> getElectionWinner(@RequestBody ElectionByIdRequest electionByIdRequest){
        return new ResponseEntity<>(electionService.getElectionWinner(electionByIdRequest), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> register(@RequestBody ElectionCreationRequest electionCreationRequest){
        return new ResponseEntity<>(electionService.register(electionCreationRequest), HttpStatus.OK);
    }
}
