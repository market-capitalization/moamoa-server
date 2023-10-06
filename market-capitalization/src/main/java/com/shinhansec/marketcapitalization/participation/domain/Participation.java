package com.shinhansec.marketcapitalization.participation.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import com.shinhansec.marketcapitalization.meeting.domain.Meeting;
import com.shinhansec.marketcapitalization.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Participation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "participation_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Builder
    public Participation(Member member, Meeting meeting) {
        this.member = member;
        this.meeting = meeting;
    }
}
