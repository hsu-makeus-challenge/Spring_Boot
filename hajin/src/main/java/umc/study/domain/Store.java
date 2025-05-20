package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.validation.annotation.ExistStore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy ="store", cascade = CascadeType.ALL)
    private List<Review> ReviewList = new ArrayList<>();

    @OneToMany(mappedBy ="store", cascade = CascadeType.ALL)
    private List<Mission> MissionList = new ArrayList<>();

    @OneToMany(mappedBy ="store", cascade = CascadeType.ALL)
    private List<StoreOpenHours> openHoursList = new ArrayList<>();

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                '}';
    }

    public void addReview(Review review) {
        this.ReviewList.add(review);
        review.setStore(this); // 연관 관계 동기화
    }

    public void addMission(Mission mission) {
        this.MissionList.add(mission);
        mission.setStore(this); // 연관 관계 동기화
    }

}