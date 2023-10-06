package com.shinhansec.marketcapitalization.meeting.controller;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponse;
import com.shinhansec.marketcapitalization.stock.dto.TradeStockReqDto;
import com.shinhansec.marketcapitalization.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting/{meetingId}")
public class MeetingTransController {
    private final StockService stockService;

    @PostMapping("/{stockId}")
    public BaseResponse<?> tradeStock(@RequestHeader("authorization") Long loginUserId,
                                      @PathVariable("meetingId") String meetingId,
                                      @PathVariable("stockId") Long stockId,
                                      @RequestBody TradeStockReqDto reqDto) {
        try {
            return new BaseResponse<>(stockService.tradeStock(loginUserId, stockId, meetingId, reqDto));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
