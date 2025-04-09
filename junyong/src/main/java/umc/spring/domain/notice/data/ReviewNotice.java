package umc.spring.domain.notice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class ReviewNotice extends BaseNotice{

    @Column(nullable = false)
    private Long reviewId;

}
