package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.UserMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rewardPoint;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 40)
    private String body;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy ="mission", cascade = CascadeType.ALL)
    private List<UserMission> UserMissionList = new ArrayList<>();

    public void setStore(Store store) {
        this.store = store;
    }
    public void addUserMission(UserMission userMission) {
        this.UserMissionList.add(userMission);
        userMission.setMission(this); // 연관 관계 동기화
    }

}