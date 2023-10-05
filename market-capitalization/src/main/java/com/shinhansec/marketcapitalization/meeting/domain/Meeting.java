package com.shinhansec.marketcapitalization.meeting.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

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

    private int totalInvestment;

    private int profitTarget;

    private LocalDateTime deadlineDate;
}
