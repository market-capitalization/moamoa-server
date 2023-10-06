package com.shinhansec.marketcapitalization.meeting.service;


import com.shinhansec.marketcapitalization.meeting.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
}
