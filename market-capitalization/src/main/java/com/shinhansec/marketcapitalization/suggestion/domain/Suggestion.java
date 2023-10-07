package com.shinhansec.marketcapitalization.suggestion.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Suggestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "suggestion_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    // 구현을 위해 중복 저장
    private String stockName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Suggestion(Stock stock, Member member) {
        this.stock = stock;
        this.stockName = stock.getStockName();
        this.member = member;
    }
}
