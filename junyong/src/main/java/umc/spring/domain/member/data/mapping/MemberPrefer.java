package umc.spring.domain.member.data.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.member.data.FoodCategory;
import umc.spring.domain.member.data.Member;
import umc.spring.global.common.data.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cateogry_id")
    private FoodCategory foodCategory;

}
