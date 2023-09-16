package com.torin.votersapp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoterRegistrationResponse {

    private String eligibilityStatus;
    private String voterCardNumber;
}
