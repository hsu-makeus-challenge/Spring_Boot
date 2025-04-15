package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.Review;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewReply extends BaseEntity {
    @Id
    @JoinColumn(name = "review_num")
    @OneToOne(fetch = FetchType.LAZY)
    private Review review; // 리뷰 번호

    @Column(nullable = false,length = 500)
    private String content;
}
