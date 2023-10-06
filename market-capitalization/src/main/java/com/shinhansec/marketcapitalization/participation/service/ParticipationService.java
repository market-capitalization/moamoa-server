package com.shinhansec.marketcapitalization.participation.service;

import com.shinhansec.marketcapitalization.participation.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
}
