package com.shinhansec.marketcapitalization.member.service;

import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}
