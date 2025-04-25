package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionId;
import umc.study.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 문의번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 10)
    private String type;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isReplied;

    //양방향 매핑
    @OneToOne(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private InquiryReply inquiryReply;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private List<InquiryImage> inquiryImages = new ArrayList<>();
}
