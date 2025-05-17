package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.UserMissionStatus;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'INPROGRESS'")
    private UserMissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_mission_id")
    private StoreMission storeMission;

    @Override
    public String toString() {
        return "UserMission {" +
                "name =" + storeMission.getStore().getName() +
                ", content='" + storeMission.getMission().getContent() + '\'' +
                ", reward='" + storeMission.getMission().getReward() + '\'' +
                '}';
    }

    public void setUser(User newUser) {
        if(this.user != null) {
            user.getUserMissionList().remove(this);
        }
        this.user = newUser;
        user.getUserMissionList().add(this);
    }

    public void setStoreMission(StoreMission newStoreMission) {
        this.storeMission = newStoreMission;
    }

    public void updateStatus() {
        this.status = UserMissionStatus.COMPLETE;
    }
}
