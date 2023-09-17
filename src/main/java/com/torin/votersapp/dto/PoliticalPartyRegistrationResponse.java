package com.torin.votersapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoliticalPartyRegistrationResponse{
    String response;

    String politicalPartyIdentificationNumber;

}
