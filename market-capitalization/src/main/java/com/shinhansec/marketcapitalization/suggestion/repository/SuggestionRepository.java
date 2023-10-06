package com.shinhansec.marketcapitalization.suggestion.repository;

import com.shinhansec.marketcapitalization.suggestion.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}
