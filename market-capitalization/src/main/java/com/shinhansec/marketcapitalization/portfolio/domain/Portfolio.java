package com.shinhansec.marketcapitalization.portfolio.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.shinhansec.marketcapitalization.common.BaseEntityStatus.ACTIVE;
import static com.shinhansec.marketcapitalization.common.BaseEntityStatus.INACTIVE;
import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.INVALID_SELLING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Portfolio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "portfolio_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Long holdingQuantity = 0L;
    private Long averagePurchasePrice = 0L;

    @Builder
    public Portfolio(Meeting meeting, Stock stock) {
        this.meeting = meeting;
        this.stock = stock;
    }

    public void sellStock(Long quantity, Long currentStockValue) throws BaseException {
        if (this.holdingQuantity < quantity) {
            throw new BaseException(INVALID_SELLING);
        }
        long totalPrice = this.holdingQuantity * this.averagePurchasePrice;
        totalPrice = totalPrice - (quantity * currentStockValue);
        this.holdingQuantity -= quantity;
        this.averagePurchasePrice = totalPrice / this.holdingQuantity;
        if (this.holdingQuantity <= 0) {
            this.setStatus(INACTIVE);
        }
    }

    public void buyStock(Long holdingQuantity, Long currentStockValue) throws BaseException {
        this.setStatus(ACTIVE);
        long totalPrice = this.holdingQuantity * this.averagePurchasePrice;
        totalPrice += holdingQuantity * currentStockValue;
        this.holdingQuantity += holdingQuantity;
        this.averagePurchasePrice = totalPrice / this.holdingQuantity;
    }

}
