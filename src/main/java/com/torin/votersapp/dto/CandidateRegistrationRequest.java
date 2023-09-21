package com.torin.votersapp.dto;

import com.torin.votersapp.data.models.Address;
import com.torin.votersapp.data.models.Election;
import com.torin.votersapp.data.models.PoliticalParty;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateRegistrationRequest {

    private String firstName;
    private String lastName;
    private Address address;
    private String birthDate;
    private String birthMonth;
    private String birthYear;
    private String email;
    private String password;
    private String gender;
    private String electionType;
    private String politicalPartyIdentificationNumber;
    private String ElectionIdentificationNumber;

}
