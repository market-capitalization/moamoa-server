package com.shinhansec.marketcapitalization.portfolio.controller;

import com.shinhansec.marketcapitalization.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;
}
