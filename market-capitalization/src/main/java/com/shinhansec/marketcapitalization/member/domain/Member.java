package com.shinhansec.marketcapitalization.member.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    @Builder
    public Member(String nickname) {
        this.nickname = nickname;
    }
}
