package com.shinhansec.marketcapitalization.stock.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import com.shinhansec.marketcapitalization.member.domain.Gender;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    private String stockName;

    @Enumerated(STRING)
    private Gender gender;

    private String age;

    private String keyword;
}
