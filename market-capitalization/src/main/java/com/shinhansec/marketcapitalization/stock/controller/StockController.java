package com.shinhansec.marketcapitalization.stock.controller;

import com.shinhansec.marketcapitalization.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {
    private StockService stockService;
}
