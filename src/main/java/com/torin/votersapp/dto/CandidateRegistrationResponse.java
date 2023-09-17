package com.torin.votersapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRegistrationResponse {
    private String eligibilityStatus;
    private String candidateIdentificationNumber;
}
