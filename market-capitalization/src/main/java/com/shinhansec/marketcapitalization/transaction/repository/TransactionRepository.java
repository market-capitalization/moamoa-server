package com.shinhansec.marketcapitalization.transaction.repository;

import com.shinhansec.marketcapitalization.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
