package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.Review;

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
    private long id;

    @Column(nullable = false,length = 50)
    private String address;

    @Column(nullable = false,length = 15)
    private String name;

    @Column(nullable = false,columnDefinition = "INTEGER DEFAULT 0")
    private Float score;

    @Column(nullable = false)
    private String storeCategory; // user 테이블의 카테고리와 어떻게 구분할까

    @Column(nullable = false)
    private String region;

    @Column(nullable = false , columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isOpened;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<Review> reviewsList = new ArrayList<>();

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<StoreImage> storeImageList = new ArrayList<>();

}
