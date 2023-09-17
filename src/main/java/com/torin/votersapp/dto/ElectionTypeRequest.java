package com.torin.votersapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElectionTypeRequest {
    private  String electionType;
}
