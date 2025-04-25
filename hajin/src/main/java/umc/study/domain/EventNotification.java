package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.UserAgree;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EventNotification extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 40)
    private String body;

//    @OneToMany(mappedBy ="event", cascade = CascadeType.ALL)
//    private List<Notification> NotificationList = new ArrayList<>();

}
