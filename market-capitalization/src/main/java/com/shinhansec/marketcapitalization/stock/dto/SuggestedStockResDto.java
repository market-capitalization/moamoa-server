package com.shinhansec.marketcapitalization.stock.dto;

import com.shinhansec.marketcapitalization.stock.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestedStockResDto {
    private String keyword;
    private List<SuggestedStock> suggestedStockList = new ArrayList<>();

    public SuggestedStockResDto(String keyword) {
        this.keyword = keyword;
    }

    public void appendSuggestedStock(List<Pair<Stock, Boolean>> stockPairList) {
        for (Pair<Stock, Boolean> pair : stockPairList) {
            SuggestedStock suggestedStock = new SuggestedStock(pair.a, pair.b);
            suggestedStockList.add(suggestedStock);
        }
    }

    @Data
    public static class SuggestedStock {
        private Long stockId;
        private String stockName;
        private Boolean isLiked;

        public SuggestedStock(Stock stock, Boolean isLiked) {
            this.stockId = stock.getId();
            this.stockName = stock.getStockName();
            this.isLiked = isLiked;
        }
    }
}
