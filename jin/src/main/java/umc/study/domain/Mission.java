package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.MissionByUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String content;

    private LocalDate deadline;

    private Integer point;

    @OneToMany(mappedBy = "mission_id", cascade = CascadeType.ALL)
    private List<MissionByUser> userMissionList = new ArrayList<>();

    // 연관관계 편의 메서드
    public void setStore(Store store) {
        this.store = store;
        store.getStoreMissionList().add(this);
    }
}

