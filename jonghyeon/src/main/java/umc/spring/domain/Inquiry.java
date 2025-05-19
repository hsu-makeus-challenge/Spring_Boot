package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public enum InquiryType {
        type1,type2,type3
    }

    @ColumnDefault("'type1'")
    @Enumerated(value = EnumType.STRING)
    private InquiryType inquiryType;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    @Builder.Default
    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private List<InquiryRely> inquiryRelyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private List<InquiryImage> inquiryImageList = new ArrayList<>();

}
