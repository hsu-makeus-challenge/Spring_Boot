package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.mapping.Review;
import umc.study.domain.mapping.UserMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자 고유번호

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)//enum 타입
    @Column(columnDefinition = "VARCHAR(10)" )
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String personalCategory;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer point;

    @Column(nullable = false)
    private Boolean isTermsAgreed;

    @Column(nullable = false)
    private Boolean isPrivacyPolicyAgreed;

    @Column(nullable = false)
    private Boolean isLocationInfoAgreed;

    @Column(nullable = false)
    private Boolean isMarketingAgreed;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus active;

    //양방향 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Inquiry> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PhoneVerification phoneVerification;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserNotificationAgreement userNotificationAgreement;




}