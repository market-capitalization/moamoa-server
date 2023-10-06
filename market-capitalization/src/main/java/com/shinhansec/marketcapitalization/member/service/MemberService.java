package com.shinhansec.marketcapitalization.member.service;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponseStatus;
import com.shinhansec.marketcapitalization.member.domain.Member;
import com.shinhansec.marketcapitalization.member.dto.ModifyUserReqDto;
import com.shinhansec.marketcapitalization.member.dto.NewMemberReqDto;
import com.shinhansec.marketcapitalization.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public BaseResponseStatus saveMember(NewMemberReqDto reqDto) throws BaseException {
        try {
            if (checkDuplicateNick(reqDto.getNickname()))
                throw new BaseException(DUPLICATE_NICK);
            Member newMember = reqDto.toEntity();
            memberRepository.save(newMember);
            return SUCCESS;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public BaseResponseStatus modifyMember(Long memberId, ModifyUserReqDto reqDto) throws BaseException {
        try {
            Member member = memberRepository.findById(memberId).orElseThrow(
                    () -> new BaseException(INVALID_MEMBER_ID)
            );
            member.modifyMember(reqDto.getAge(), reqDto.getGender(), reqDto.getNickname());
            return SUCCESS;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public Boolean checkDuplicateNick(String nickname) throws BaseException {
        try {
            return memberRepository.existsByNickname(nickname);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
