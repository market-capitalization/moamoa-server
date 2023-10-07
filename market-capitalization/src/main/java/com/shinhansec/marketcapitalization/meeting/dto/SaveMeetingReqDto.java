package com.shinhansec.marketcapitalization.meeting.dto;

import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.domain.ParticipantType;
import com.shinhansec.marketcapitalization.meeting.domain.PurposeType;
import com.shinhansec.marketcapitalization.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveMeetingReqDto {
    private PurposeType purposeType;
    private ParticipantType participantType;
    private int profitTarget;
    private LocalDateTime deadlineDate;
    private String meetingName;
    private int attendanceCount;

    public Meeting toEntity() {
        return Meeting.builder()
                .purposeType(purposeType)
                .participantType(participantType)
                .profitTarget(profitTarget)
                .deadlineDate(deadlineDate)
                .name(meetingName)
                .attendanceCount(attendanceCount)
                .build();
    }
}
