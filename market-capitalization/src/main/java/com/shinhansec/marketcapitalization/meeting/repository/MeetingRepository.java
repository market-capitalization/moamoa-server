package com.shinhansec.marketcapitalization.meeting.repository;

import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, String> {
}
