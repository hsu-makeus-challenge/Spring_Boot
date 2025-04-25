package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.StoreFoodCategory;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String city; // 시 (서울특별시, 전라남도)

    @Column(nullable = false, length = 10)
    private String district; // 군·구 (성북구, 수원시)

    @Column(nullable = false, length = 10)
    private String neighborhood; // 동 (삼성동, 안암동)

    @Builder.Default
    @OneToMany(mappedBy = "region", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Store> storeList  = new ArrayList<>();
}
