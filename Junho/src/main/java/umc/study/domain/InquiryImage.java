package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "inquiry_num")
    @ManyToOne(fetch = FetchType.LAZY) // 하나의 Inquiry에 대해 여러개 생성 가능
    private Inquiry inquiry;

    @Column(nullable = false)
    private String imageUrl;
}
