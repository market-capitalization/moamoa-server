package com.shinhansec.marketcapitalization.transaction.service;

import com.shinhansec.marketcapitalization.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
}
