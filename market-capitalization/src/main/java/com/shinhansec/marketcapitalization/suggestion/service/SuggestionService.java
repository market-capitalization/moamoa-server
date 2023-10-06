package com.shinhansec.marketcapitalization.suggestion.service;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.repository.MeetingRepository;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.repository.MemberRepository;
import com.shinhansec.marketcapitalization.stock.domain.Stock;
import com.shinhansec.marketcapitalization.stock.repository.StockRepository;
import com.shinhansec.marketcapitalization.stock.service.RecommendedStocksResDto;
import com.shinhansec.marketcapitalization.suggestion.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.*;

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


            List<Pair<Stock, Boolean>> pairList = stockRepository.findMostRecommendedStockInMeeting(meeting).stream().map(
                    stock -> {
                        Boolean isExist = suggestionRepository.existsByMemberAndStock(member, stock);
                        return new Pair<>(stock, isExist);
                    }
            ).collect(Collectors.toList());

            return RecommendedStocksResDto.builder()
                    .recommendedCategory("test category")
                    .stockSuggestionPairList(pairList)
                    .build();

        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
