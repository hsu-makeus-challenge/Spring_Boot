package umc.spring.domain.notice.data;

import jakarta.persistence.*;
import lombok.Getter;
import umc.spring.global.common.data.BaseEntity;

@Getter
@MappedSuperclass
public abstract class BaseNotice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title = "";

    @Column(nullable = false, length = 50)
    private String content = "";

    @Column(nullable = false)
    private Boolean isConfirmed = false;

}
