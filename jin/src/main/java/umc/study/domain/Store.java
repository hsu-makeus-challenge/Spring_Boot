package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

    private Integer ownerNum;

    private String storeName;

    private String foodCategory;

    private Boolean openStatus;

    private Float rateAvg;

    private String Address;

    private String location;

    @OneToMany(mappedBy = "store_id",cascade = CascadeType.ALL)
    private List<Review> storeReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "store_id", cascade = CascadeType.ALL)
    private List<Mission> storeMissionList = new ArrayList<>();
}
