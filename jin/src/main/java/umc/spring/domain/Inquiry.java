package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.InquiryType;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 30)
    private String title;

    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false)
    private Boolean isAnswered = false; // 기본값 설정

    // 연관관계 편의 메서드
    public void setUser(User user) {
        this.user = user;
        user.getUserInquiryList().add(this);
    }
}
