package com.shinhansec.marketcapitalization.meeting.service;


import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponseStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.dto.GetAllMeetingResDto;
import com.shinhansec.marketcapitalization.meeting.dto.MeetingDetailResDto;
import com.shinhansec.marketcapitalization.meeting.dto.SaveMeetingReqDto;
import com.shinhansec.marketcapitalization.meeting.repository.MeetingRepository;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.repository.MemberRepository;
import com.shinhansec.marketcapitalization.participation.domain.Participation;
import com.shinhansec.marketcapitalization.participation.repository.ParticipationRepository;
import com.shinhansec.marketcapitalization.portfolio.domain.Portfolio;
import com.shinhansec.marketcapitalization.portfolio.repository.PortfolioRepository;
import com.shinhansec.marketcapitalization.stock.service.RecommendedStocksResDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shinhansec.marketcapitalization.common.BaseEntityStatus.ACTIVE;
import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;
    private final ParticipationRepository participationRepository;
    private final PortfolioRepository portfolioRepository;

    public GetAllMeetingResDto getAllMeetingByMember(Long userId) throws BaseException {
        try {

            Member member = memberRepository.findById(userId).orElseThrow(
                    () -> new BaseException(INVALID_MEMBER_ID));

            List<Meeting> meetingsByMember = meetingRepository.findMeetingsByMember(member);

            GetAllMeetingResDto getAllMeetingResDto = new GetAllMeetingResDto();
            getAllMeetingResDto.addMeetingInMeetingResDtoList(meetingsByMember);

            return getAllMeetingResDto;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public String saveMeeting(SaveMeetingReqDto meetingReqDto, Long userId) throws BaseException {
        try {

            Member member = memberRepository.findById(userId).orElseThrow(
                    () -> new BaseException(INVALID_MEMBER_ID));

            Meeting newMeeting = meetingReqDto.toEntity();
            meetingRepository.save(newMeeting);

            Participation participation = Participation.builder()
                    .member(member)
                    .meeting(newMeeting).build();
            participationRepository.save(participation);

            return newMeeting.getId();
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public BaseResponseStatus participateMeeting(Long userId, String meetingId) throws BaseException {
        try {
            Member member = memberRepository.findById(userId).orElseThrow(
                    () -> new BaseException(INVALID_MEMBER_ID));

            Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(
                    () -> new BaseException(INVALID_MEETING_ID));

            if (checkIsParticipate(member, meeting)) {
                throw new BaseException(ALREADY_PARTICIPATE);
            }

            Participation participation = Participation.builder()
                    .member(member)
                    .meeting(meeting).build();
            participationRepository.save(participation);

            return SUCCESS;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // TODO: test 필요
    public MeetingDetailResDto getMeetingDetail(Long userId, String meetingId) throws BaseException {
        try {
            Member member = memberRepository.findById(userId).orElseThrow(
                    () -> new BaseException(INVALID_MEMBER_ID));

            Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(
                    () -> new BaseException(INVALID_MEETING_ID));

            if (!checkIsParticipate(member, meeting)) {
                throw new BaseException(NOT_MEETING_MEMBER);
            }

            List<Portfolio> portfolioList = portfolioRepository.findActivePortfoliosByMeeting(meeting, ACTIVE);

            return MeetingDetailResDto.builder()
                    .meeting(meeting)
                    .portfolioList(portfolioList)
                    .build();
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public Boolean checkIsParticipate(Member member, Meeting meeting) {
        return participationRepository.existsByMemberAndMeeting(member, meeting);
    }
}
