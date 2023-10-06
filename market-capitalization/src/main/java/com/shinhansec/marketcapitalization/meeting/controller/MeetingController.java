package com.shinhansec.marketcapitalization.meeting.controller;

import com.shinhansec.marketcapitalization.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;
}
