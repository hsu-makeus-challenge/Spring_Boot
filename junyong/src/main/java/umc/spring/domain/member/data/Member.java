package umc.spring.domain.member.data;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.data.mapping.MemberPrefer;
import umc.spring.domain.notice.data.EventNotice;
import umc.spring.domain.notice.data.InquiryNotice;
import umc.spring.domain.notice.data.ReviewNotice;
import umc.spring.global.common.data.BaseEntity;
import umc.spring.domain.member.data.enums.Gender;
import umc.spring.domain.member.data.enums.MemberStatus;
import umc.spring.domain.member.data.enums.SocialType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus memberStatus;

    @Column(nullable = false, length = 10)
    private String birth;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @ColumnDefault("0") // 점수 기본값 0
    private Integer point;

    @Column(nullable = false, length = 15, unique = true)
    private String phone;

    @Column(nullable = false)
    private Boolean locationAgree;

    @Column(nullable = false)
    private Boolean marketingAgree;

    // 매핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventNotice> eventNoticeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InquiryNotice> inquiryNoticeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewNotice> reviewNoticeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissionList = new ArrayList<>();

}