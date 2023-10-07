package com.shinhansec.marketcapitalization.stock.controller;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponse;
import com.shinhansec.marketcapitalization.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {
    private final StockService stockService;

    @GetMapping("")
    public BaseResponse<?> getSuggestedStock(@RequestHeader("authorization") Long loginUserId) {
        try {
            return new BaseResponse<>(stockService.getSuggestedStock(loginUserId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
