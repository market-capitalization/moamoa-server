package com.shinhansec.marketcapitalization.meeting.repository;

import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, String> {
    @Query(value = "select m from Meeting m left join m.participationList as ml " +
            "where ml.member=:user_id")
    List<Meeting> findMeetingsByMember(@Param("user_id") Member member);
}
