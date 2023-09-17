package com.torin.votersapp.data.repository;

import com.torin.votersapp.data.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByElection_Type(String electionType);

    Candidate findByCandidateIdentificationNumber(String candidateIdentificationNumber);

    Candidate findByEmail(String email);
}
