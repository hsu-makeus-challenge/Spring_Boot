package umc.spring.domain.notice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class EventNotice extends BaseNotice {

    @Column(nullable = false)
    private Long eventId;

}
