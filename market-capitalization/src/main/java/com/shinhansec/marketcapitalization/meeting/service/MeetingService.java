package com.shinhansec.marketcapitalization.meeting.service;


import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponseStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.dto.GetAllMeetingResDto;
import com.shinhansec.marketcapitalization.meeting.dto.SaveMeetingReqDto;
import com.shinhansec.marketcapitalization.meeting.repository.MeetingRepository;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.repository.MemberRepository;
import com.shinhansec.marketcapitalization.participation.domain.Participation;
import com.shinhansec.marketcapitalization.participation.repository.ParticipationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;
    private final ParticipationRepository participationRepository;

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

}
