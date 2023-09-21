package com.torin.votersapp.data.repository;

import com.torin.votersapp.data.models.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty,Long> {


    PoliticalParty findByPoliticalPartyIdentificationNumber(String politicalPartIdentificationNumber);
}
