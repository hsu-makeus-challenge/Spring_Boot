package umc.study.domain;

import io.micrometer.observation.Observation;
import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.NoticeType;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoticeType type; // 예: NOTICE, MARKETING

    private boolean isConfirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventNotification eventNotification;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private InquiryNotification inquiryNotification;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewNotification reviewNotification;

}
