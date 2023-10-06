package com.shinhansec.marketcapitalization.member.controller;

import com.shinhansec.marketcapitalization.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
}
