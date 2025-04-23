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
    private String city; // 시

    @Column(nullable = false, length = 10)
    private String district; // 군·구

    @Column(nullable = false, length = 10)
    private String neighborhood; // 동

    @Builder.Default
    @OneToMany(mappedBy = "region", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Store> storeList  = new ArrayList<>();
}
