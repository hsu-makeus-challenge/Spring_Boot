package umc.spring.domain.member.data;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.member.data.enums.PointType;
import umc.spring.global.common.data.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PointHistory extends BaseEntity {

    // 필드들
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private Integer balance;

    @Column(nullable = false)
    private Boolean isSuccess = false;

    // 포인트 교환 유형 필드 추가, 기본값은 기타(etc)
    @Column(nullable = false)
    private PointType actionType = PointType.ETC;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
