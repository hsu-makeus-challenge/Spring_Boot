package umc.spring.domain.member.data.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.store.data.Store;
import umc.spring.global.common.data.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_store", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "store_id"})
})
public class MemberStore extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
