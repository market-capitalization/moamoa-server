package com.shinhansec.marketcapitalization.transaction.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    private int quantity;
    private int price;

    @Enumerated(STRING)
    private TransactionType transactionType;
}
