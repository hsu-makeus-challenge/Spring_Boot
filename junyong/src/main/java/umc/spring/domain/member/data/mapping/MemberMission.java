package umc.spring.domain.member.data.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.global.common.data.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // 기본값 = 도전중
    private MissionStatus status = MissionStatus.CHALLENGING;

    @Column(nullable = false, unique = true)
    private String confirmNumber; // 데이터 생성 시 값 유일한 랜덤 생성

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "mission_id")
    private Mission mission;

    // 연관관계 편의메서드
    public void setMember(Member member) {
        if (this.member != null) {
            member.getMemberMissionList().remove(this);
        }
        this.member = member;
        member.getMemberMissionList().add(this);
    }

    public void setMission(Mission mission) {
        if(this.mission != null) {
            mission.getMemberMissionList().remove(this);
        }
        this.mission = mission;
        mission.getMemberMissionList().add(this);
    }

    public void changeStatus(MissionStatus newStatus) {
        this.status = newStatus;
    }


}
