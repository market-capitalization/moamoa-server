package com.shinhansec.marketcapitalization.meeting.dto;

import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.domain.ParticipantType;
import com.shinhansec.marketcapitalization.meeting.domain.PurposeType;
import com.shinhansec.marketcapitalization.portfolio.domain.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDetailResDto {

    private String meetingName;

    private ParticipantType particiPantType;

    private PurposeType purposeType;

    private int totalInvestment;

    private int profitTarget;

    // d-day
    private int deadlineDate;

    private List<CurrentPortfolio> currentPortfolios = new ArrayList<>();

    public static class CurrentPortfolio {
        private String stockName;
        private Long holdingQuantity;
        private Long averagePurchasePrice;

        public CurrentPortfolio(Portfolio portfolio) {
            this.stockName = portfolio.getStock().getStockName();
            this.holdingQuantity = portfolio.getHoldingQuantity();
            this.averagePurchasePrice = portfolio.getAveragePurchasePrice();
        }
    }

    @Builder
    public MeetingDetailResDto(Meeting meeting, List<Portfolio> portfolioList) {
        this.meetingName = meeting.getName();
        this.particiPantType = meeting.getParticiPantType();
        this.purposeType = meeting.getPurposeType();
        this.totalInvestment = meeting.getTotalInvestment();
        this.profitTarget = meeting.getProfitTarget();
        this.deadlineDate = Period.between(LocalDateTime.now().toLocalDate(), meeting.getDeadlineDate().toLocalDate()).getDays();

        for (Portfolio portfolio : portfolioList) {
            this.currentPortfolios.add(new CurrentPortfolio(portfolio));
        }
    }
}
