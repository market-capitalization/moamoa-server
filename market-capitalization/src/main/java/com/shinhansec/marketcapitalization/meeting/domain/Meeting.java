package com.shinhansec.marketcapitalization.meeting.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import com.shinhansec.marketcapitalization.participation.domain.Participation;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
public class Meeting extends BaseEntity {
    @Id
    @Column(name = "meeting_id")
    @GenericGenerator(name = "idGenerator", strategy = "com.shinhansec.marketcapitalization.common.generator.RandomStringGeneratedKey")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String name;

    private int attendanceCount;

    @Enumerated(STRING)
    private ParticipantType particiPantType;

    @Enumerated(STRING)
    private PurposeType purposeType;

    // for demo
    private int totalInvestment = 50000000;

    private int profitTarget;

    private LocalDateTime deadlineDate;

    @OneToMany(mappedBy = "meeting")
    private List<Participation> participationList;

    public int participate(Participation participation) {
        if (checkParticipateEnable()) {
            participationList.add(participation);
            return 1;
        }
        return -1;
    }

    private Boolean checkParticipateEnable() {
        return this.participationList.size() < this.attendanceCount;
    }
}
