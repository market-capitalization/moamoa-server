package com.shinhansec.marketcapitalization.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendedStocksResDto {
    private int count;
    private List<RecommendedStock> recommendedStockList = new ArrayList<>();

    @Builder
    public RecommendedStocksResDto(List<RecommendedStock> recommendedStockList) {
        this.count = recommendedStockList.size();
        this.recommendedStockList = recommendedStockList;
    }

    @Data
    public static class RecommendedStock {
        private String stockName;
        private Boolean isLiked;
        private int recommendedCount;

        @Builder
        public RecommendedStock(String stockName, Boolean isLiked, int recommendedCount) {
            this.stockName = stockName;
            this.isLiked = isLiked;
            this.recommendedCount = recommendedCount;
        }
    }

}
