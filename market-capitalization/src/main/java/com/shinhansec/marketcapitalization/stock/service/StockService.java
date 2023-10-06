package com.shinhansec.marketcapitalization.stock.service;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponseStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.repository.MeetingRepository;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.repository.MemberRepository;
import com.shinhansec.marketcapitalization.participation.repository.ParticipationRepository;
import com.shinhansec.marketcapitalization.portfolio.domain.Portfolio;
import com.shinhansec.marketcapitalization.portfolio.repository.PortfolioRepository;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import com.shinhansec.marketcapitalization.stock.dto.SuggestedStockResDto;
import com.shinhansec.marketcapitalization.stock.dto.TradeStockReqDto;
import com.shinhansec.marketcapitalization.stock.repository.StockRepository;
import com.shinhansec.marketcapitalization.suggestion.repository.SuggestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.shinhansec.marketcapitalization.common.BaseEntityStatus.ACTIVE;
import static com.shinhansec.marketcapitalization.common.BaseEntityStatus.INACTIVE;
import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.*;
import static com.shinhansec.marketcapitalization.stock.domain.StockTradeType.BUY;
import static com.shinhansec.marketcapitalization.stock.domain.StockTradeType.SELL;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final ParticipationRepository participationRepository;
    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;
    private final PortfolioRepository portfolioRepository;
    private final SuggestionRepository suggestionRepository;

    @Transactional
    public BaseResponseStatus tradeStock(Long loginUserId, Long stockId, String meetingId, TradeStockReqDto reqDto) throws BaseException {
        try {

            if (reqDto.getStockQuantity() <= 0) {
                throw new BaseException(INVALID_TRADING);
            }

            Member member = memberRepository.findById(loginUserId).orElseThrow(() -> new BaseException(INVALID_MEMBER_ID));
            Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() -> new BaseException(INVALID_MEETING_ID));
            Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new BaseException(INVALID_STOCK_ID));

            if (!checkMemberIsInMeeting(member, meeting))
                throw new BaseException(NOT_YOUR_MEETING);

            Portfolio portfolio;
            if (portfolioRepository.existsByMeetingAndStock(meeting, stock)) {
                // exists 확인했으므로 그냥 get() 해줌.
                portfolio = portfolioRepository.findByMeetingAndStock(meeting, stock).get();
//                portfolio.setStatus(ACTIVE); // logic error
                if (portfolio.getStatus().equals(INACTIVE) && reqDto.getStockTradeType().equals(SELL)) {
                    throw new BaseException(INVALID_SELLING);
                }
            } else {
                if (reqDto.getStockTradeType().equals(SELL)) {
                    throw new BaseException(INVALID_SELLING);
                }
                portfolio = Portfolio.builder()
                        .meeting(meeting)
                        .stock(stock)
                        .build();
                portfolioRepository.save(portfolio);
            }

            long tradePrice = reqDto.getStockQuantity() * reqDto.getCurrentStockValue();

            if (reqDto.getStockTradeType().equals(SELL)) {
                sellStock(portfolio, reqDto); // 보유량 먼저 확인
                meeting.sellStock(tradePrice);
            } else if (reqDto.getStockTradeType().equals(BUY)) {
                meeting.buyStock(tradePrice); // 잔고 먼저 확인
                buyStock(portfolio, reqDto);
            }

            return SUCCESS;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void sellStock(Portfolio portfolio, TradeStockReqDto reqDto) throws BaseException {
        portfolio.sellStock(reqDto.getStockQuantity(), reqDto.getCurrentStockValue());
    }

    public void buyStock(Portfolio portfolio, TradeStockReqDto reqDto) throws BaseException {
        portfolio.buyStock(reqDto.getStockQuantity(), reqDto.getCurrentStockValue());
    }

    public boolean checkMemberIsInMeeting(Member member, Meeting meeting) {
        return participationRepository.existsByMemberAndMeetingAndStatus(member, meeting, ACTIVE);
    }

    public SuggestedStockResDto getSuggestedStock(Long loginUserId) throws BaseException {
        try {
            Member member = memberRepository.findById(loginUserId).orElseThrow(() -> new BaseException(INVALID_MEMBER_ID));

            String age = member.getAge() + " ";
            char[] ageCharArray = age.toCharArray();

            if (ageCharArray[1] >= 5) {
                ageCharArray[2] = 's';
            } else {
                ageCharArray[2] = 'e';
            }

            ageCharArray[1] = '0';
            age = new String(ageCharArray);

            System.out.println(age);


            // TODO: 쿼리 10개씩 날아감....!!!
            List<Pair<Stock, Boolean>> stockPairList = stockRepository.findByAgeAndGender(age, member.getGender()).stream().map(
                    stock -> {
                        Boolean isLiked = suggestionRepository.existsByMemberAndStockAndStatus(member, stock, ACTIVE);
                        return new Pair<Stock, Boolean>(stock, isLiked);
                    }
            ).collect(Collectors.toList());

            SuggestedStockResDto suggestedStockResDto = new SuggestedStockResDto(stockPairList.get(0).a.getKeyword());
            suggestedStockResDto.appendSuggestedStock(stockPairList);

            return suggestedStockResDto;

        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(e);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
