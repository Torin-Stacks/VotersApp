package com.torin.votersapp.data.repository;

import com.torin.votersapp.data.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    Election findByType(String gubernatorial);
}
