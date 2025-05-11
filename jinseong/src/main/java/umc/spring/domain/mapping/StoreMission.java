package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Override
    public String toString() {
        StringBuilder foodCategories = new StringBuilder();
        for (StoreFoodCategory sfc : store.getStoreFoodCategoryList()) {
            foodCategories.append(sfc.getFoodCategory().getName()).append(", ");
        }

        return "StoreMission {" +
                "neighborhood =" + store.getNeighborhood().getNeighborhood() +
                ", food_category='" + foodCategories + '\'' +
                ", name='" + store.getName() + '\'' +
                ", content='" + mission.getContent() + '\'' +
                ", reward='" + mission.getReward() + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }

    public void setMission(Mission newMission) {
        if(this.mission != null) {
            mission.getStoreMissionList().remove(this);
        }
        this.mission = newMission;
        mission.getStoreMissionList().add(this);
    }

    public void setStore(Store newStore) {
        if(this.store != null) {
            store.getStoreMissionList().remove(this);
        }
        this.store = newStore;
        store.getStoreMissionList().add(this);
    }
}
