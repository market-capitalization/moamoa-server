package com.shinhansec.marketcapitalization.stock.service;

import com.shinhansec.marketcapitalization.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
}
