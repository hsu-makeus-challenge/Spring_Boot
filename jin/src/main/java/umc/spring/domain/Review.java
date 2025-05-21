package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private Float rate;

    @Column(nullable = false, length = 100)
    private String content;

    // 연관관계 편의 메서드
    public void setStore(Store store) {
        if(this.store != null)
            store.getReviewList().remove(this);
        this.store = store;
        store.getReviewList().add(this);
    }

    public void setUser(User user) {
        if(this.user != null)
            user.getReviewList().remove(this);
        this.user = user;
        store.getReviewList().add(this);
    }

}
