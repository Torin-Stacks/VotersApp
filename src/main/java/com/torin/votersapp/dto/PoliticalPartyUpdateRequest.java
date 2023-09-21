package com.torin.votersapp.dto;

import com.torin.votersapp.data.models.Candidate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PoliticalPartyUpdateRequest {
    private String name;
    private List<Candidate> candidates;
    private String politicalPartIdentificationNumber;
}
