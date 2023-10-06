package com.shinhansec.marketcapitalization.portfolio.repository;

import com.shinhansec.marketcapitalization.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
