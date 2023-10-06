package com.shinhansec.marketcapitalization.portfolio.service;

import com.shinhansec.marketcapitalization.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private PortfolioRepository portfolioRepository;
}
