package com.shinhansec.marketcapitalization.member.controller;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponse;
import com.shinhansec.marketcapitalization.member.dto.ModifyUserReqDto;
import com.shinhansec.marketcapitalization.member.dto.NewMemberReqDto;
import com.shinhansec.marketcapitalization.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.NOT_YOUR_PROFILE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    public BaseResponse<?> makeNewMember(@RequestBody NewMemberReqDto reqDto) {
        try {
            return new BaseResponse<>(memberService.saveMember(reqDto));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PatchMapping("/{memberId}")
    public BaseResponse<?> modifyUserInfo(@RequestHeader("authorization") Long loginId,
                                          @PathVariable("memberId") Long memberId,
                                          @RequestBody ModifyUserReqDto reqDto) {
        try {
            if (!loginId.equals(memberId)) {
                throw new BaseException(NOT_YOUR_PROFILE);
            }
            return new BaseResponse<>(memberService.modifyMember(memberId, reqDto));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/duplicate")
    public BaseResponse<?> checkNickDuplicate(@RequestParam("nickname") String nickname) {
        try {
            return new BaseResponse<>(memberService.checkDuplicateNick(nickname));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
