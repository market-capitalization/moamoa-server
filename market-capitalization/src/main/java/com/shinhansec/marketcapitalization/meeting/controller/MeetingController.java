package com.shinhansec.marketcapitalization.meeting.controller;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponse;
import com.shinhansec.marketcapitalization.meeting.dto.GetAllMeetingResDto;
import com.shinhansec.marketcapitalization.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting")
public class MeetingController {
    private final MeetingService meetingService;

    @GetMapping("")
    public BaseResponse<GetAllMeetingResDto> getAllMeeting(@RequestHeader("authorization") Long userId) {
        try {
            return new BaseResponse<>(meetingService.getAllMeetingByMember(userId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
