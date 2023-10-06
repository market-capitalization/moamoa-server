package com.shinhansec.marketcapitalization.stock.service;

import com.shinhansec.marketcapitalization.stock.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendedStocksResDto {
    private String recommendedCategory;
    private int count;
    private List<RecommendedStock> recommendedStockList = new ArrayList<>();

    @Builder
    public RecommendedStocksResDto(String recommendedCategory, List<Pair<Stock, Boolean>> stockSuggestionPairList) {
        this.recommendedCategory = recommendedCategory;
        this.count = stockSuggestionPairList.size();
        for (Pair<Stock, Boolean> stockBooleanPair : stockSuggestionPairList) {
            this.recommendedStockList.add(new RecommendedStock(stockBooleanPair));
        }
    }

    public static class RecommendedStock {
        private Long stockId;
        private String stockName;
        private Boolean isLiked;

        public RecommendedStock(Pair<Stock, Boolean> pair) {
            this.stockId = pair.a.getId();
            this.stockName = pair.a.getStockName();
            this.isLiked = pair.b;
        }
    }

}