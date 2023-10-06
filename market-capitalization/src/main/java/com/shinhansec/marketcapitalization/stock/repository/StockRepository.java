package com.shinhansec.marketcapitalization.stock.repository;

import com.shinhansec.marketcapitalization.common.BaseEntityStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.member.domain.Gender;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // TODO: 테스트 필요
    @Query(value = "select distinct s from Stock s where s.id in " +
            "(select su.id from Suggestion su where su.member in " +
            "(select p.member from Participation p where p.meeting = :meeting)) and " +
            "s.status = :status")
    List<Stock> findMostRecommendedStockInMeeting(@Param("meeting") Meeting meeting,
                                                  @Param("status") BaseEntityStatus status);

    List<Stock> findByAgeAndGender(String age, Gender gender);
}
