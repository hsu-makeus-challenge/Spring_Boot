package umc.spring.domain.mapping;


import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MissionByUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @Column(columnDefinition = "DEFAULT FALSE")
    private Boolean isCompleted;

    // 연관관게 편의 메서드
    public void setMission(Mission mission) {
        this.mission = mission;
        mission.getUserMissionList().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getUserMissionList().add(this);
    }
}
