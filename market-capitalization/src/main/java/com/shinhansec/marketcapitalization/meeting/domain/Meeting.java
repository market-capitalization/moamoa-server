package com.shinhansec.marketcapitalization.meeting.domain;

import com.shinhansec.marketcapitalization.common.BaseEntity;
import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.participation.domain.Participation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.shinhansec.marketcapitalization.common.BaseResponseStatus.INVALID_BUYING;
import static com.shinhansec.marketcapitalization.meeting.domain.ParticipantType.FAMILY;
import static com.shinhansec.marketcapitalization.meeting.domain.PurposeType.RETIREMENT;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@NoArgsConstructor
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
    private Long totalInvestment = 50000000L;

    private int profitTarget;

    private LocalDateTime deadlineDate;

    @OneToMany(mappedBy = "meeting")
    private List<Participation> participationList = new ArrayList<>();

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

    public void buyStock(long price) throws BaseException {
        if (this.totalInvestment < price)
            throw new BaseException(INVALID_BUYING);
        this.totalInvestment -= price;
    }

    public void sellStock(long price) {
        this.totalInvestment += price;
    }

    @Builder(builderMethodName = "testBuild")
    public Meeting(String name, int attendanceCount, LocalDateTime deadlineDate) {
        this.name = name;
        this.attendanceCount = attendanceCount;
        this.deadlineDate = deadlineDate;
    }

    @Builder
    public Meeting(String name, int attendanceCount,
                   ParticipantType particiPantType, PurposeType purposeType,
                   int profitTarget, LocalDateTime deadlineDate) {
        this.name = name;
        this.attendanceCount = attendanceCount;
        this.particiPantType = particiPantType;
        this.purposeType = purposeType;
        this.profitTarget = profitTarget;
        this.deadlineDate = deadlineDate;
    }
}
