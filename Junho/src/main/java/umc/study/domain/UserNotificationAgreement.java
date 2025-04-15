package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserNotificationAgreement extends BaseEntity {

    @Id
    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean event;

    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean review;

    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean inquiry;

}
