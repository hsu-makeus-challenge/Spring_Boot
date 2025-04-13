package umc.spring.domain.notice.data;

import jakarta.persistence.*;
import umc.spring.domain.member.data.Member;

@Entity
public class ReviewNotice extends BaseNotice{

    @Column(nullable = false)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
