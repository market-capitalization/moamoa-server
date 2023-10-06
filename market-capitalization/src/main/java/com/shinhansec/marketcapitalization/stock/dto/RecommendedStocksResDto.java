package com.shinhansec.marketcapitalization.stock.dto;

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
    private int count;
    private List<RecommendedStock> recommendedStockList = new ArrayList<>();

    @Builder
    public RecommendedStocksResDto(List<Pair<Stock, Boolean>> stockSuggestionPairList) {
        this.count = stockSuggestionPairList.size();
        for (Pair<Stock, Boolean> stockBooleanPair : stockSuggestionPairList) {
            this.recommendedStockList.add(new RecommendedStock(stockBooleanPair));
        }
    }

    @Data
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
