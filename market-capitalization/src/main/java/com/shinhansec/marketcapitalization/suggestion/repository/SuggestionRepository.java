package com.shinhansec.marketcapitalization.suggestion.repository;

import com.shinhansec.marketcapitalization.common.BaseEntityStatus;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import com.shinhansec.marketcapitalization.suggestion.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    Boolean existsByMemberAndStock(Member member, Stock stock);

    Boolean existsByMemberAndStockAndStatus(Member member, Stock stock, BaseEntityStatus status);

    Optional<Stock> findByMemberAndStock(Member member, Stock stock);
}
