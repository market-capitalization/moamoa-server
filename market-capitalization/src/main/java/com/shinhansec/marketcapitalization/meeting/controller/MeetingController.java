package com.shinhansec.marketcapitalization.meeting.controller;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponse;
import com.shinhansec.marketcapitalization.meeting.dto.GetAllMeetingResDto;
import com.shinhansec.marketcapitalization.meeting.dto.SaveMeetingReqDto;
import com.shinhansec.marketcapitalization.meeting.service.MeetingService;
import com.shinhansec.marketcapitalization.suggestion.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting")
public class MeetingController {
    private final MeetingService meetingService;
    private final SuggestionService suggestionService;

    @GetMapping("")
    public BaseResponse<GetAllMeetingResDto> getAllMeeting(@RequestHeader("authorization") Long userId) {
        try {
            return new BaseResponse<>(meetingService.getAllMeetingByMember(userId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("")
    public BaseResponse<?> makeNewMeeting(@RequestHeader("authorization") Long userId,
                                          @RequestBody SaveMeetingReqDto reqDto) {
        try {
            return new BaseResponse<>(meetingService.saveMeeting(reqDto, userId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/{meetingId}")
    public BaseResponse<?> getMeetingDetails(@RequestHeader("authorization") Long userId,
                                             @PathVariable("meetingId") String meetingId) {
        try {
            return new BaseResponse<>(meetingService.getMeetingDetail(userId, meetingId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


    @PostMapping("/{meetingId}")
    public BaseResponse<?> participateMeeting(@RequestHeader("authorization") Long userId,
                                              @PathVariable("meetingId") String meetingId) {
        try {
            return new BaseResponse<>(meetingService.participateMeeting(userId, meetingId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/{meetingId}/recommend")
    public BaseResponse<?> getMostRecommend(@RequestHeader("authorization") Long userId,
                                            @PathVariable("meetingId") String meetingId) {
        try {
            return new BaseResponse<>(suggestionService.getMostRecommendedStocks(userId, meetingId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
