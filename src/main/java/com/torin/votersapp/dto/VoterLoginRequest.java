package com.torin.votersapp.dto;

import lombok.Builder;
import lombok.Data;

@Data

public class VoterLoginRequest {
    private String email;
    private String password;
    private String voterCardNumber;

}
