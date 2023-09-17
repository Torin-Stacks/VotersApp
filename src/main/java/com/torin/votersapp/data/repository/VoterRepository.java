package com.torin.votersapp.data.repository;

import com.torin.votersapp.data.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    Voter findByEmail(String email);

    Voter findByVotersCardNumber(String votersCardNumber);
}

