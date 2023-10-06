package com.shinhansec.marketcapitalization.stock.dto;

import com.shinhansec.marketcapitalization.stock.domain.StockTradeType;
import lombok.Data;

@Data
public class TradeStockReqDto {
    private Long stockQuantity;
    private Long currentStockValue;
    private StockTradeType stockTradeType;
}
