package com.torin.votersapp.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ElectionCreationRequest {
    private String type;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean electionStillOn;
}
