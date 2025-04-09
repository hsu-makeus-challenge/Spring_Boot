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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "mission_id")
    private Mission mission;

}
