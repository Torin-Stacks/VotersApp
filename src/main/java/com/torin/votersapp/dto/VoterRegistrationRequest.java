package com.torin.votersapp.dto;

import com.torin.votersapp.data.models.Address;
import lombok.Data;

@Data

public class VoterRegistrationRequest {
    private String firstName;
    private String lastName;
    private Address address;
    private String birthDate;
    private String birthMonth;
    private String birthYear;
    private String email;
    private String gender;

}
