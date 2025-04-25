package umc.spring.domain.mapping;


import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 100)
    private String message;

    private Boolean isRead;

    // 연관관계 편의 메서드
    public void setUser(User user) {
        this.user = user;
        user.getUserAlarmList().add(this);
    }
}
