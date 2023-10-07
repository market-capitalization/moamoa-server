package com.shinhansec.marketcapitalization.participation.service;

import com.shinhansec.marketcapitalization.participation.domain.Participation;
import com.shinhansec.marketcapitalization.participation.repository.ParticipationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    @Transactional
    public void saveParticipation(Participation participation) {
        participationRepository.save(participation);
    }
}
