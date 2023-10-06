package com.shinhansec.marketcapitalization.stock.controller;

import com.shinhansec.marketcapitalization.common.BaseResponse;
import com.shinhansec.marketcapitalization.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {
    private StockService stockService;
}
