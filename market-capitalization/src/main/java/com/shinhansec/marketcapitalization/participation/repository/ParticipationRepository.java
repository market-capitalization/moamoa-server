package com.shinhansec.marketcapitalization.participation.repository;

import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.participation.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
