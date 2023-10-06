package com.shinhansec.marketcapitalization.meeting.service;

import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.service.MemberService;
import com.shinhansec.marketcapitalization.participation.domain.Participation;
import com.shinhansec.marketcapitalization.participation.service.ParticipationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MeetingServiceTest {

    @Autowired
    MeetingService meetingService;

    @Autowired
    MemberService memberService;

    @Autowired
    ParticipationService participationService;

    @Test
    public void getAllMeetingsTest() throws Exception {
        // given
        Meeting testMeetingTest = Meeting.testBuild()
                .deadlineDate(LocalDateTime.now())
                .name("test meeting test")
                .attendanceCount(1).build();

        meetingService.saveMeeting(testMeetingTest);


        Member member = new Member("test user 1");
        Participation participation = Participation.builder()
                .member(member)
                .meeting(testMeetingTest).build();

        memberService.saveMember(member);
        participationService.saveParticipation(participation);

        // when
        testMeetingTest.participate(participation);

        // then
    }

}