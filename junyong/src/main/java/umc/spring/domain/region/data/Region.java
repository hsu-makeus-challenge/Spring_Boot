package umc.spring.domain.region.data;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.common.data.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String regionCode; // ex) SL115(서울의 어딘가), CN095(충남 어딘가), BS7019(부산 어딘가)

    @Column(nullable = false, length = 20)
    private String name;

}
