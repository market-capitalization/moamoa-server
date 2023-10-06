package com.shinhansec.marketcapitalization.participation.controller;

import com.shinhansec.marketcapitalization.participation.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationService participationService;
}
