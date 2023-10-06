package com.shinhansec.marketcapitalization.suggestion.service;

import com.shinhansec.marketcapitalization.suggestion.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;
}
