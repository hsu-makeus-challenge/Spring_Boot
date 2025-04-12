package umc.spring.domain.inquiry.data;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.notice.data.InquiryNotice;
import umc.spring.global.common.data.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry extends BaseEntity {

    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title = "untitled";

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    private Boolean isAnswered = false; // 기본값 false

    // 매핑
    @OneToOne(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private InquiryNotice inquiryNotice;
}
