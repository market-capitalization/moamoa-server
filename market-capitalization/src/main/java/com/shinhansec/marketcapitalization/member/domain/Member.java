package com.shinhansec.marketcapitalization.member.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;
}
