package com.shinhansec.marketcapitalization.stock.repository;

import com.shinhansec.marketcapitalization.common.BaseEntityStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // TODO: 테스트 필요
    @Query(value = "select distinct s from Stock s right join Suggestion su where su.status = :status and " +
            "su.member = (select mem from Member mem where mem = (select p.member from Participation p where p.meeting = :meeting)) " +
            "order by count(s.id)")
    List<Stock> findMostRecommendedStockInMeeting(Meeting meeting, BaseEntityStatus status);
}
