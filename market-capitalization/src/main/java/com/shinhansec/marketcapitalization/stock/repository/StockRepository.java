package com.shinhansec.marketcapitalization.stock.repository;

import com.shinhansec.marketcapitalization.common.BaseEntityStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.member.domain.Gender;
import com.shinhansec.marketcapitalization.stock.domain.RecommendedStockRepoInterface;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query(value =  "select su.stock.id as stockId, su.stockName as stockName, count(su.stockName) as total from Suggestion as su " +
            "where su.member in " +
            "(select p.member from Participation as p where p.meeting = :meeting) and su.status = :status " +
            "group by su.stockName order by total desc")
    List<RecommendedStockRepoInterface> findMostRecommendedStockInMeeting(@Param("meeting") Meeting meeting,
                                                                          @Param("status") BaseEntityStatus status);

    List<Stock> findByAgeAndGender(String age, Gender gender);
}
