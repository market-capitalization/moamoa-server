package com.shinhansec.marketcapitalization.stock.repository;

import com.shinhansec.marketcapitalization.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
