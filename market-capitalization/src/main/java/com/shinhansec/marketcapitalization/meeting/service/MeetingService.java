package com.shinhansec.marketcapitalization.meeting.service;


import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponseStatus;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.dto.GetAllMeetingResDto;
import com.shinhansec.marketcapitalization.meeting.repository.MeetingRepository;
import com.shinhansec.marketcapitalization.participation.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final ParticipationRepository participationRepository;

    public GetAllMeetingResDto getAllMeetingByMember(Long userId) throws BaseException {
        try {
            List<Meeting> meetingsByMember = meetingRepository.findMeetingsByMember(userId);

            GetAllMeetingResDto getAllMeetingResDto = new GetAllMeetingResDto();
            getAllMeetingResDto.addMeetingInMeetingResDtoList(meetingsByMember);

            return getAllMeetingResDto;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
