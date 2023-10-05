package com.shinhansec.marketcapitalization.participation.repository;

import com.shinhansec.marketcapitalization.participation.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
