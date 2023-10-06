package com.shinhansec.marketcapitalization.participation.repository;

import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.participation.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    Boolean existsByMemberAndMeeting(Member member, Meeting meeting);
}
