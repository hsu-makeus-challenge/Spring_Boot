package umc.spring.domain.mission.data;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.store.data.Store;
import umc.spring.global.common.data.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String content; // 미션내용

    @Column(nullable = false)
    private Integer reward = 10; // 값 안 넣으면 10포인트

    // 기간을 무한으로 한다고 했을 때, null로 두도록 설정
    private LocalDate deadline;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
