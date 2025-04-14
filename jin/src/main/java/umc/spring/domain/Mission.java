package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MissionByUser;

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

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false)
    private Integer point;

    @Column(columnDefinition = "DEFAULT FALSE")
    private Boolean isReviewed;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MissionByUser> userMissionList = new ArrayList<>();

    // 연관관계 편의 메서드
    public void setStore(Store store) {
        this.store = store;
        store.getStoreMissionList().add(this);
    }
}

