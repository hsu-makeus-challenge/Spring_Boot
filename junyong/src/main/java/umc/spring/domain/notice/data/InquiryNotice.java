package umc.spring.domain.notice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class InquiryNotice extends BaseNotice{

    @Column(nullable = false)
    private Long missionId;

}
