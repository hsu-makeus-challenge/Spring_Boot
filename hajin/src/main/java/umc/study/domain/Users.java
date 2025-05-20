package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.SocialType;
import umc.study.domain.enums.UserStatus;
import umc.study.domain.mapping.FoodPreference;
import umc.study.domain.mapping.UserAgree;
import umc.study.domain.mapping.UserMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate // update시 null인경우 쿼리를 보내지 않음
@DynamicInsert // insert시 null인경우 쿼리를 보내지 않음
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
@AllArgsConstructor // 모든 매개변수 생성자
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // jpa가 통신을 하는 dbms의 방식을 따른다는 의미.
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private LocalDateTime birth;

    private int age;

    @Column(nullable = false, length = 40)
    private String address;

    @ColumnDefault("0")
    private int point;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 40)
    private String phone_number;

    private boolean phone_certification;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING) //ORDINAL(순서저장)->STRING
    @Column(nullable = false, length = 20)
    private SocialType socialType;

    private LocalDateTime inactiveDate;

    @OneToMany(mappedBy ="user", cascade =CascadeType.ALL)
    private List<UserAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy ="user", cascade =CascadeType.ALL)
    private List<FoodPreference> foodPreferenceList = new ArrayList<>();

    @OneToMany(mappedBy ="user", cascade =CascadeType.ALL)
    private List<Inquiry> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy ="user", cascade =CascadeType.ALL)
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy ="user", cascade =CascadeType.ALL)
    private List<UserMission> userMissionList  = new ArrayList<>();

    //CascadeType.All -> User의 변화에 따라 Review, FoodPreference 등의 entity가 영향을 받는다.

    public void addUserMission(UserMission usermission) {
        this.userMissionList.add(usermission);
        usermission.setUser(this); // 연관 관계 동기화
    }
}
