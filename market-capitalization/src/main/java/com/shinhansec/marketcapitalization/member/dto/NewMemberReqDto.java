package com.shinhansec.marketcapitalization.member.dto;

import com.shinhansec.marketcapitalization.member.domain.Gender;
import com.shinhansec.marketcapitalization.member.domain.Member;
import lombok.Data;

@Data
public class NewMemberReqDto {
    private String nickname;
    private int age;
    private Gender gender;

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .age(age)
                .gender(gender)
                .build();
    }
}
