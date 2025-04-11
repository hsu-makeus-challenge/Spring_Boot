package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;

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
    private Region regionId;

    private Integer ownerNum;

    private String storeName;

    private String foodCategory;

    private Boolean openStatus;

    private Float rateAvg;

    private String Address;

    private String location;

}
