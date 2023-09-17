package com.torin.votersapp.dto;

import lombok.Data;

@Data
public class CandidateLoginRequest {
    private String email;
    private String password;
    private String candidateIdentificationNumber;
}
