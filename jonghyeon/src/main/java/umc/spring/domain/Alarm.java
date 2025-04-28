package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Alarm extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public enum AlarmType {
        event, review, inquiry
    }

    @ColumnDefault("'event'")
    @Enumerated(value = EnumType.STRING)
    private AlarmType alarmType;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false, length = 50)
    private String title;
}
