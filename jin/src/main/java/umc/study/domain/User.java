package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.enums.SocialType;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialLoginType;

    private String email;

    private Boolean isServiceAgreed;

    private Boolean isPrivateAgreed;

    private Boolean isLocationAgreed;

    private Boolean isMarketingAgreed;

    private Boolean alarmAgreed;

    private String address;

    private Integer point;

    private String phoneNumber;

    private String specAddress;

    private Boolean isPhoneVerified;

    private LocalDate inactiveDate;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private LocalDate inActiveDate;

    private LocalDate birth;
}
