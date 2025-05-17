package umc.spring.domain.review.data;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.review.data.enums.ReviewStatus;
import umc.spring.domain.store.data.Store;
import umc.spring.global.common.data.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false) // 내가 졌다
//    private Long member_id; // 유저 매핑 안 함

    @Column(nullable = false, length = 50)
    private String content = "";

    @Column(nullable = false)
    private Float score = 0.0f;

    @Enumerated(value = EnumType.STRING )
    private ReviewStatus status = ReviewStatus.CREATED;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ReviewReply> replyList = new ArrayList<>();

}
