package com.shinhansec.marketcapitalization.meeting.service;

import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.meeting.repository.MeetingRepository;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.repository.MemberRepository;
import com.shinhansec.marketcapitalization.member.service.MemberService;
import com.shinhansec.marketcapitalization.participation.domain.Participation;
import com.shinhansec.marketcapitalization.participation.service.ParticipationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class MeetingServiceTest {

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ParticipationService participationService;

    @Test
    public void getAllMeetingsTest() throws Exception {
        // given
        Meeting testMeetingTest = Meeting.testBuild()
                .deadlineDate(LocalDateTime.now())
                .name("test meeting 2")
                .attendanceCount(1).build();

        meetingRepository.save(testMeetingTest);

        Member member = memberRepository.findById(1L).get();
        Participation participation = Participation.builder()
                .member(member)
                .meeting(testMeetingTest).build();

        memberService.saveMember(member);
        participationService.saveParticipation(participation);

        // when
//        testMeetingTest.participate(participation);

        // then
    }

}