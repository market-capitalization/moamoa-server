package com.shinhansec.marketcapitalization.member.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private int age;

    @Enumerated(STRING)
    private Gender gender;

    private String nickname;

    public void modifyMember(int age, Gender gender, String nickname) {
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
    }

    @Builder
    public Member(int age, Gender gender, String nickname) {
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
    }
}
