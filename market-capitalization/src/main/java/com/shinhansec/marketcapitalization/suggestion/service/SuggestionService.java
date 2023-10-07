package com.shinhansec.marketcapitalization.suggestion.service;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponseStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.repository.MeetingRepository;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.repository.MemberRepository;
import com.shinhansec.marketcapitalization.stock.domain.RecommendedStockRepoInterface;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import com.shinhansec.marketcapitalization.stock.dto.RecommendedStocksResDto;
import com.shinhansec.marketcapitalization.stock.repository.StockRepository;
import com.shinhansec.marketcapitalization.suggestion.domain.Suggestion;
import com.shinhansec.marketcapitalization.suggestion.repository.SuggestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.shinhansec.marketcapitalization.common.BaseEntityStatus.ACTIVE;
import static com.shinhansec.marketcapitalization.common.BaseEntityStatus.INACTIVE;
import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.*;
import static com.shinhansec.marketcapitalization.stock.dto.RecommendedStocksResDto.RecommendedStock;

@Service
@RequiredArgsConstructor
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;
    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;
    private final StockRepository stockRepository;

    public RecommendedStocksResDto getMostRecommendedStocks(Long userId, String meetingId) throws BaseException {
        try {
            Member member = memberRepository.findById(userId).orElseThrow(
                    () -> new BaseException(INVALID_MEMBER_ID));

            Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(
                    () -> new BaseException(INVALID_MEETING_ID));


            List<RecommendedStockRepoInterface> mostRecommendedStockInMeeting
                    = stockRepository.findMostRecommendedStockInMeeting(meeting, ACTIVE);

            List<RecommendedStock> recommendedStockList = new ArrayList<>();
            for (RecommendedStockRepoInterface repoInterface : mostRecommendedStockInMeeting) {
                Boolean isLiked = suggestionRepository.existsByMemberAndStockNameAndStatus(member, repoInterface.getStockName(), ACTIVE);
                RecommendedStock stock
                        = new RecommendedStock(repoInterface.getStockId(), repoInterface.getStockName(), isLiked, repoInterface.getTotal());
                recommendedStockList.add(stock);
            }

            return new RecommendedStocksResDto(recommendedStockList);

        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public BaseResponseStatus saveNewSuggestion(Long userId, Long stockId) throws BaseException {
        try {
            Member member = memberRepository.findById(userId).orElseThrow(
                    () -> new BaseException(INVALID_MEMBER_ID));

            Stock stock = stockRepository.findById(stockId).orElseThrow(
                    () -> new BaseException(INVALID_STOCK_ID));

            // member - stock name pair 당 하나씩만 생성함.
            if (suggestionRepository.existsByMemberAndStockName(member, stock.getStockName())) {
                List<Stock> findStock = suggestionRepository.findByMemberAndStockNameOrderByCreatedDate(
                        member, stock.getStockName());
                if (findStock.get(0).getStatus().equals(ACTIVE)) {
                    findStock.get(0).setStatus(INACTIVE);
                } else {
                    findStock.get(0).setStatus(ACTIVE);
                }
            } else {
                Suggestion newSuggestion = Suggestion.builder()
                        .member(member)
                        .stock(stock)
                        .build();
                suggestionRepository.save(newSuggestion);
            }
            return SUCCESS;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
