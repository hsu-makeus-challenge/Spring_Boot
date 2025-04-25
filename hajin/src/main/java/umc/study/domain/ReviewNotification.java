package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import umc.study.domain.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewNotification extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 40)
    private String body;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

//    @OneToMany(mappedBy ="review_notification", cascade = CascadeType.ALL)
//    private List<Notification> NotificationList = new ArrayList<>();
}
