package com.shinhansec.marketcapitalization.suggestion.repository;

import com.shinhansec.marketcapitalization.common.BaseEntityStatus;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import com.shinhansec.marketcapitalization.suggestion.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
//    Boolean existsByMemberAndStock(Member member, Stock stock);

    Boolean existsByMemberAndStockName(Member member, String stockName);
//    Boolean existsByMemberAndStockAndStatus(Member member, Stock stock, BaseEntityStatus status);
    Boolean existsByMemberAndStockNameAndStatus(Member member, String stockName, BaseEntityStatus status);

    List<Stock> findByMemberAndStockNameOrderByCreatedDate(Member member, String stockName);
}
