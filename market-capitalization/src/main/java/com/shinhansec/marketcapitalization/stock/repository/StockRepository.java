package com.shinhansec.marketcapitalization.stock.repository;

import com.shinhansec.marketcapitalization.common.BaseEntityStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.member.domain.Gender;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.temporal.ValueRange;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // TODO: 테스트 필요
    @Query(value = "select distinct s from Stock s left join Suggestion su on su.status =:status and " +
            "su.member = any (select p.member from Participation as p where p.meeting = :meeting)")
    List<Stock> findMostRecommendedStockInMeeting(Meeting meeting, BaseEntityStatus status);

    List<Stock> findByAgeAndGender(String age, Gender gender);
}
