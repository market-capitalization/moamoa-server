package com.shinhansec.marketcapitalization.meeting.dto;

import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMeetingResDto {
    List<MeetingResDto> meetingList = new ArrayList<>();

    public void addMeetingInMeetingResDtoList(List<Meeting> meetingList) {
        for (Meeting meeting : meetingList) {
            this.meetingList.add(new MeetingResDto(meeting));
        }
    }

    public static class MeetingResDto {
        public String meetingId;
        public String meetingName;
        public int investmentReturn;
        public int profitTarget;

        public MeetingResDto(Meeting meeting) {
            this.meetingId = meeting.getId();
            this.meetingName = meeting.getName();
            this.investmentReturn = 0;
            this.profitTarget = meeting.getProfitTarget();
        }
    }
}
