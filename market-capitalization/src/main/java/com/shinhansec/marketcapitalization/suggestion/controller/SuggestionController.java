package com.shinhansec.marketcapitalization.suggestion.controller;

import com.shinhansec.marketcapitalization.suggestion.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuggestionController {
    private final SuggestionService suggestionService;
}
