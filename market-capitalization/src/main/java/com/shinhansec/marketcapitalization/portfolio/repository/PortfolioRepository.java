package com.shinhansec.marketcapitalization.portfolio.repository;

import com.shinhansec.marketcapitalization.common.BaseEntityStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query(value = "select p from Portfolio p join fetch p.stock as s " +
            "where p.meeting = :meeting and p.status =: status")
    List<Portfolio> findActivePortfoliosByMeeting(@Param(value = "meeting") Meeting meeting,
                                                  @Param(value = "status") BaseEntityStatus status);
}
