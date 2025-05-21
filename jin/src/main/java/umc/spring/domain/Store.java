package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.StoreCategory;

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
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(nullable = false)
    private Integer ownerNum;

    @Column(nullable = false, length = 50)
    private String storeName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreCategory category;

    @Column(nullable = false)
    private Boolean openStatus;

    @Column(nullable = false)
    private Float score = 0.0f;

    @Column(nullable = false, length = 50)
    private String address;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> storeMissionList = new ArrayList<>();

    // 연관관계 편의 메서드
    public void assignRegion(Region region) {
        this.region = region;
    }
}
